package br.edu.mariaregina.agenda_dmos5.model;

import androidx.annotation.NonNull;

public class Contato {
    private String nome;
    private String sobrenome;
    private int telefonefixo;
    private int telefonecelular;


    public Contato(String nome, String sobrenome,int telefonefixo, int telefonecelular) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefonefixo = telefonefixo;
        this.telefonecelular = telefonecelular;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenomeome() {
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


    @NonNull
    @Override
    public String toString() {
        return String.format("RA: %s/n %s/n %d\n %d\n", nome , sobrenome,telefonefixo,telefonecelular);
    }
}


