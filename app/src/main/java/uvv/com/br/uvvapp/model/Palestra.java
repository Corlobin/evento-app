package uvv.com.br.uvvapp.model;

import java.io.Serializable;
import java.util.List;

public class Palestra implements Serializable {
    private String data;
    private String hora;
    private String informacao;
    private List<Professor> professores;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public String getHora() {
        return hora;
    }

    public String getInformacao() {
        return informacao;
    }

    public String toHtml() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("<b>(%s)</b> - %s", hora, informacao));
        return stringBuilder.toString();
    }
}