package br.vvs.gabrielEx2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ticketman {

    public int numeroDeShows;
    private List<Show> shows;

    public Ticketman() {
        shows = new ArrayList<>();
        numeroDeShows = 0;
    }

    public Show criarShow(Calendar dataShow1, String art1, int cache1, int despesas1, boolean especial) {
        Show show = new Show(dataShow1, art1, cache1, despesas1, especial);
        shows.add(show);
        numeroDeShows++;
        return show;
    }

    public int getNumeroDeShows() {
        return numeroDeShows;
    }

    public Show getShow(String id) {
        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getID().equals(id)) {
                shows.get(i).toString();
                return shows.get(i);
            }
        }
        throw new IllegalArgumentException("Show não existe");
    }
}
