package br.vvs.gabrielEx2;

import java.util.Calendar;
import java.util.List;

public class Show {
    private String id;
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
        String day = String.format("%02d", data.get(Calendar.DAY_OF_MONTH));
        String month = String.format("%02d", data.get(Calendar.MONTH) + 1);
        String year = String.format("%04d", data.get(Calendar.YEAR));
        this.id = day + month + year + artista;
        //System.out.println(id);


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

    public String getID() {
        return id;
    }

    public boolean isEspecial() {
        return especial;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public String toString() {
        return artista + id;
    }
}
