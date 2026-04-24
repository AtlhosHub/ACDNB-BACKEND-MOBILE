package com.teste.acdnb.infrastructure.dto;

import java.util.List;

public class AIChatDTO {


    public static class AlunoResumoDTO {
        private int id;
        private String nome;
        private String nivel;
        private List<String> observacoes;

        public AlunoResumoDTO() {}

        public AlunoResumoDTO(int id, String nome, String nivel, List<String> observacoes) {
            this.id = id;
            this.nome = nome;
            this.nivel = nivel;
            this.observacoes = observacoes;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getNivel() { return nivel; }
        public void setNivel(String nivel) { this.nivel = nivel; }

        public List<String> getObservacoes() { return observacoes; }
        public void setObservacoes(List<String> observacoes) { this.observacoes = observacoes; }
    }

    public static class GerarPlanoRequest {
        private String message;
        private List<AlunoResumoDTO> students;

        public GerarPlanoRequest() {}

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public List<AlunoResumoDTO> getStudents() { return students; }
        public void setStudents(List<AlunoResumoDTO> students) { this.students = students; }
    }

    public static class TranscricaoRequest {
        private String audioBase64;
        private String mimeType;
        private List<AlunoResumoDTO> students;

        public TranscricaoRequest() {}

        public String getAudioBase64() { return audioBase64; }
        public void setAudioBase64(String audioBase64) { this.audioBase64 = audioBase64; }

        public String getMimeType() { return mimeType; }
        public void setMimeType(String mimeType) { this.mimeType = mimeType; }

        public List<AlunoResumoDTO> getStudents() { return students; }
        public void setStudents(List<AlunoResumoDTO> students) { this.students = students; }
    }
}