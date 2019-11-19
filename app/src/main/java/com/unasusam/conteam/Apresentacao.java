package com.unasusam.conteam;

import android.net.Uri;

import java.io.Serializable;

public class Apresentacao implements Serializable {

    private String nome;
    private String title;
    private String id;
    transient
    private Uri uri;


    Apresentacao(String nome, String titulo_apresentacao, String id){
        this.nome = nome;
        this.title = titulo_apresentacao;
        this.id = id;
    }
    Apresentacao(){}
    public String getNome_apresentador() {
        return nome;
    }

    public void setNome_apresentador(String nome_apresentador) {
        this.nome = nome_apresentador;
    }

    public String getTitulo_apresentacao() {
        return title;
    }

    public void setTitulo_apresentacao(String titulo_apresentacao) {
        this.title = titulo_apresentacao;
    }

    public Uri getFoto_apresentador() {
        return uri;
    }

    public void setFoto_apresentador(Uri foto_apresentador) {
        this.uri = foto_apresentador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
