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

    public Lote criarLote(String showID, int vip, int meia, int normal, int desconto) {
        return getShow(showID).criarLote(vip, meia, normal, desconto);
    }

    public int getNumeroDeShows() {
        return numeroDeShows;
    }

    public Show getShow(String id) {
        for(int i = 0; i < shows.size(); i++) {
            if(shows.get(i).getID().equals(id)) {
                return shows.get(i);
            }
        }
        throw new IllegalArgumentException("Show não existe");
    }

    public List<Lote> getLotes(String showID) {
        return getShow(showID).getLotes();
    }

    public String getLotesString(String showID) {
        List<Lote> lotes = getShow(showID).getLotes();
        StringBuilder lotesStringBuilder = new StringBuilder();

        for (Lote lote : lotes) {
            lotesStringBuilder.append(lote.toString()).append("\n");
        }

        return lotesStringBuilder.toString();
    }

    public Ingresso comprarIngresso(String showID, String lote1ID, String tipo) {
        System.out.println("Comprando Ingresso...");
        if(tipo.equals("VIP")) {
            System.out.println("VIP");
            return getShow(showID).getLote(lote1ID).comprarIngresso(TipoIngresso.VIP);
        }
        if(tipo.equals("MEIA")) {
            System.out.println("MEIA");
            return getShow(showID).getLote(lote1ID).comprarIngresso(TipoIngresso.MEIA);

        }
        if(tipo.equals("NORMAL")) {
            System.out.println("NORMAL");
            return getShow(showID).getLote(lote1ID).comprarIngresso(TipoIngresso.NORMAL);

        }
        throw new IllegalArgumentException("Ingresso Esgotado");
    }
}
