package com.teste.acdnb.core.domain.aluno;

public class Nivel {
    private int id;
    private String descricao;

    public Nivel(int id, String descricao) {
        if(descricao == null || descricao.isEmpty()){
            throw new IllegalArgumentException("A descrição do nível não pode ficar em branco");
        }
        this.id = id;
        this.descricao = descricao;
    }

    public Nivel() {
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
