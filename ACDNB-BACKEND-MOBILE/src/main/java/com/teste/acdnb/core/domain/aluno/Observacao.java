package com.teste.acdnb.core.domain.aluno;

public class Observacao {
    private int id;
    private String descricao;
    private Aluno aluno;

    public Observacao(int id, String descricao, Aluno aluno){
        this.id = id;
        this.descricao = descricao;
        this.aluno = aluno;
    }

    public Observacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
