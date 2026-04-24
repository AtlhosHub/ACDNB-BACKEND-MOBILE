package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.GerarPlanoGateway;
import com.teste.acdnb.infrastructure.dto.AIChatDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ClaudeGateway implements GerarPlanoGateway {

    @Value("${gemini.api.key}")
    private String apiKey;


    private final RestTemplate restTemplate;

    public ClaudeGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String gerarPlano(String message, List<AIChatDTO.AlunoResumoDTO> students) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", apiKey.trim());

        Map<String, Object> body = Map.of(
                "contents", List.of(Map.of(
                        "parts", List.of(Map.of("text", buildPrompt(message, students)))
                ))
        );

        System.out.println("API KEY: [" + apiKey + "]");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao chamar Gemini: " + response.getStatusCode());
        }

        return extractText(response.getBody());
    }


    private String buildPrompt(String message, List<AIChatDTO.AlunoResumoDTO> students) {
        if (students == null || students.isEmpty()) {
            return """
                Você é um assistente especialista em treino de tênis de mesa.
                Solicitação do treinador: "%s".
                Responda em português de forma prática e objetiva.
                """.formatted(message);
        }

        String perfis = students.stream()
                .map(aluno -> {
                    String obs = (aluno.getObservacoes() == null || aluno.getObservacoes().isEmpty())
                            ? "Sem observações registradas"
                            : aluno.getObservacoes().stream()
                            .map(o -> "    • " + o)
                            .collect(Collectors.joining("\n"));

                    return """
                        - %s (Nível: %s)
                          Observações:
                        %s
                        """.formatted(aluno.getNome(), labelNivel(aluno.getNivel()), obs);
                })
                .collect(Collectors.joining("\n"));

        return """
            Você é um assistente especialista em treino de tênis de mesa.
 
            Alunos selecionados com seus perfis completos:
            %s
            Solicitação do treinador: "%s"
 
            Com base nos perfis e observações de cada aluno, gere um plano de treino
            detalhado e personalizado em português. Seja prático e objetivo.
            Organize por seções: aquecimento, exercícios principais e volta à calma.
            Adapte os exercícios às limitações e características de cada aluno.
            """.formatted(perfis, message);
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

    private String labelNivel(String nivel) {
        if (nivel == null) return "Não informado";
        return switch (nivel) {
            case "Beginner"     -> "Iniciante";
            case "Intermediate" -> "Intermediário";
            case "Advanced"     -> "Avançado";
            default             -> nivel;
        };
    }
}