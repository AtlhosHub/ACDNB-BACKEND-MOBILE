package com.teste.acdnb.core.domain.censo;

import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;

public class Censo {
    private String id;
    private String nome_municipio;
    private Float num_responsaveis;
    private Float num_habitantes;
    private Float var_num_habitantes;
    private Float renda_media_responsavel;
    private Float var_renda_responsavel;
    private Float latitude;
    private Float longitude;
    private DataInclusao dataInclusao;

    public Censo(){}

    public Censo(String id, String nome_municipio, Float num_responsaveis, Float num_habitantes, Float var_num_habitantes, Float renda_media_responsavel, Float var_renda_responsavel, Float latitude, Float longitude, DataInclusao dataInclusao) {
        this.id = id;
        this.nome_municipio = nome_municipio;
        this.num_responsaveis = num_responsaveis;
        this.num_habitantes = num_habitantes;
        this.var_num_habitantes = var_num_habitantes;
        this.renda_media_responsavel = renda_media_responsavel;
        this.var_renda_responsavel = var_renda_responsavel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataInclusao = dataInclusao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome_municipio() {
        return nome_municipio;
    }

    public void setNome_municipio(String nome_municipio) {
        this.nome_municipio = nome_municipio;
    }

    public Float getNum_responsaveis() {
        return num_responsaveis;
    }

    public void setNum_responsaveis(Float num_responsaveis) {
        this.num_responsaveis = num_responsaveis;
    }

    public Float getNum_habitantes() {
        return num_habitantes;
    }

    public void setNum_habitantes(Float num_habitantes) {
        this.num_habitantes = num_habitantes;
    }

    public Float getVar_num_habitantes() {
        return var_num_habitantes;
    }

    public void setVar_num_habitantes(Float var_num_habitantes) {
        this.var_num_habitantes = var_num_habitantes;
    }

    public Float getRenda_media_responsavel() {
        return renda_media_responsavel;
    }

    public void setRenda_media_responsavel(Float renda_media_responsavel) {
        this.renda_media_responsavel = renda_media_responsavel;
    }

    public Float getVar_renda_responsavel() {
        return var_renda_responsavel;
    }

    public void setVar_renda_responsavel(Float var_renda_responsavel) {
        this.var_renda_responsavel = var_renda_responsavel;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public DataInclusao getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(DataInclusao dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}