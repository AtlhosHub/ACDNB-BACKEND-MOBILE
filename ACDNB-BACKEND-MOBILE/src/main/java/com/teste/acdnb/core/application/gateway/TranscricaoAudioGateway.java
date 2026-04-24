package com.teste.acdnb.core.application.gateway;

public interface TranscricaoAudioGateway {
    String transcrever(String audioBase64, String mimeType);
}
