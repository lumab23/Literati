package com.aula.literatiapp.model;

public class Community {
    private String nome;
    private String imagemUri;
    private String descricao;
    private String categoria;

    public Community(String nome, String imagemUri, String descricao, String categoria){
        this.nome = nome;
        this.imagemUri = imagemUri;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUri() {
        return imagemUri;
    }

    public void setImagemUri(String imagemUri) {
        this.imagemUri = imagemUri;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
