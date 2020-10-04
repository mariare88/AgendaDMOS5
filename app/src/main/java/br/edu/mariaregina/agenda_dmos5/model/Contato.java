package br.edu.mariaregina.agenda_dmos5.model;

import androidx.annotation.NonNull;

public class Contato {
    private String nome;
    private String sobrenome;
    private int telefonefixo;
    private int telefonecelular;
    private boolean favorito;

    public Contato(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefonefixo = telefonefixo;
        this.telefonecelular = telefonecelular;
        favorito = false;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getTelefonefixo() {
        return telefonefixo;
    }

    public void setTelefonefixo(int telefonefixo) {
        this.telefonefixo = telefonefixo;
    }

    public int getTelefonecelular() { return telefonecelular; }

    public void setTelefonecelular(int telefonecelular) {
        this.telefonecelular = telefonecelular;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void doFavotite(){
        this.favorito = true;
    }


    public void undoFavorite(){
        this.favorito = false;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }
}





