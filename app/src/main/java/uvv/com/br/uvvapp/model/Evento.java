package uvv.com.br.uvvapp.model;

import java.io.Serializable;
import java.util.List;

public class Evento implements Serializable {
    private List<Palestra> palestras;

    public List<Palestra> getPalestras() {
        return palestras;
    }

    public void setPalestras(List<Palestra> palestras) {
        this.palestras = palestras;
    }
}
