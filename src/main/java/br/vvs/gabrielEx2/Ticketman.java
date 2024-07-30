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

    public Show criarShow(Calendar dataShow1, String art1, int cache1, int despesas1, boolean b) {
        Show show = new Show(dataShow1, art1, cache1, despesas1, b);
        shows.add(show);
        numeroDeShows++;
        return show;
    }

    public int getNumeroDeShows() {
        return numeroDeShows;
    }
}
