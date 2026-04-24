package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.TranscricaoAudioGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class GeminiGateway implements TranscricaoAudioGateway {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String transcrever(String audioBase64, String mimeType) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", apiKey.trim());

        Map<String, Object> body = Map.of(
                "contents", List.of(Map.of(
                        "parts", List.of(
                                Map.of("inline_data", Map.of(
                                        "mime_type", mimeType,
                                        "data", audioBase64
                                )),
                                Map.of("text",
                                        "Transcreva exatamente o que foi dito neste áudio em português. " +
                                                "Retorne apenas a transcrição, sem explicações adicionais."
                                )
                        )
                ))
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao chamar Gemini: " + response.getStatusCode());
        }

        return extractText(response.getBody());
    }

    @SuppressWarnings("unchecked")
    private String extractText(Map body) {
        if (body == null) return "";
        List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
        if (candidates == null || candidates.isEmpty()) return "";

        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
        if (parts == null || parts.isEmpty()) return "";

        return (String) parts.get(0).get("text");
    }
}