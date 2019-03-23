package uvv.com.br.uvvapp.model;

import java.io.Serializable;

public class Professor implements Serializable {

    public Professor(String nome, String titulacao, String tema){
        this.nome = nome;
        this.titulacao = titulacao;
        this.tema = tema;
    }

    public Professor(){

    }
    private String nome;
    private String titulacao;
    private String tema;

    public String getNome() {
        return nome;
    }

    public String getTema() {
        return tema;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}
