package br.vvs.gabrielEx2;

import java.util.Calendar;
import java.util.List;

public class Show {
    private Calendar data;
    private String artista;
    private int cache;
    private int despesas;
    private boolean especial;
    private List<Lote> lotes;

    public Show(Calendar data, String artista, int cache, int despesas, boolean especial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesas = despesas;
        this.especial = especial;


    }

    public boolean equals(Show show) {
        if(show.getData().equals(this.data) && show.getArtista().equals(this.artista)
                && show.getCache() == this.cache && show.getDespesas() == this.despesas
                && show.isEspecial() == this.especial) {
            return true;
        }
        return false;
    }


    public Calendar getData() {
        return data;
    }

    public String getArtista() {
        return artista;
    }

    public int getCache() {
        return cache;
    }

    public int getDespesas() {
        return despesas;
    }

    public boolean isEspecial() {
        return especial;
    }

    public List<Lote> getLotes() {
        return lotes;
    }
}
