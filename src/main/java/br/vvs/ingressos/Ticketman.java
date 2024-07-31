package br.vvs.ingressos;

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

    public Show criarShow(Calendar dataShow, String art, int cache, int despesas, boolean especial) {
        verificarShowNulo(dataShow, art, cache, despesas, especial);
        verificarShowVazio(dataShow, art, cache, despesas);
        Show show = new Show(dataShow, art, cache, despesas, especial);
        shows.add(show);
        numeroDeShows++;
        return show;
    }

    public void verificarShowVazio(Calendar dataShow, String art, int cache, int despesas) {
        if (art == null || art.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do artista não pode estar vazio.");
        }
        if (cache <= 0) {
            throw new IllegalArgumentException("O cachê deve ser um valor positivo.");
        }
        if (despesas <= 0) {
            throw new IllegalArgumentException("As despesas devem ser um valor positivo.");
        }
    }

    public void verificarShowNulo(Calendar dataShow, String art, Integer cache, Integer despesas, Boolean especial) {
        if (dataShow == null) {
            throw new IllegalArgumentException("A data do show não pode ser nula.");
        }
        if (art == null) {
            throw new IllegalArgumentException("O nome do artista não pode ser nulo.");
        }
        if (cache == null) {
            throw new IllegalArgumentException("O cachê não pode ser nulo.");
        }
        if (despesas == null) {
            throw new IllegalArgumentException("As despesas não podem ser nulas.");
        }
        if (especial == null) {
            throw new IllegalArgumentException("O campo especial não pode ser nulo.");
        }
    }

    public Lote criarLote(String showID, int vip, int meia, int normal, int desconto, int precoIngresso) {
        verificarLotePositivo(vip, meia, normal, desconto, precoIngresso);
        return getShow(showID).criarLote(vip, meia, normal, desconto, precoIngresso);
    }

    public void verificarLotePositivo(int vip, int meia, int normal, int desconto, int precoIngresso) {
        if (vip <= 0) {
            throw new IllegalArgumentException("O VIP deve ser um valor positivo.");
        }
        if (meia <= 0) {
            throw new IllegalArgumentException("O MEIA deve ser um valor positivo.");
        }
        if (normal <= 0) {
            throw new IllegalArgumentException("O NORMAL deve ser um valor positivo.");
        }
        if (desconto < 0) {
            throw new IllegalArgumentException("O desconto deve ser um valor positivo.");
        }
        if (precoIngresso <= 0) {
            throw new IllegalArgumentException("O Preço do Ingresso deve ser um valor positivo.");
        }
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

    public Lote getLote(String showID, String loteID) {
        return getShow(showID).getLote(loteID);
    }

    public String getLotesString(String showID) {
        List<Lote> lotes = getShow(showID).getLotes();
        if(lotes.size() == 0) {
            return "Show não possui lotes.";
        }
        StringBuilder lotesStringBuilder = new StringBuilder();

        for (Lote lote : lotes) {
            lotesStringBuilder.append(lote.toString()).append("\n");
        }

        return lotesStringBuilder.toString();
    }

    /*
    public Ingresso comprarIngresso(String showID, String loteID, String tipo) {
        //System.out.println("Comprando Ingresso...");
        if(tipo.equals("VIP")) {
            //System.out.println("VIP");
            return getShow(showID).comprarIngresso(loteID, TipoIngresso.VIP);
        }
        if(tipo.equals("MEIA")) {
            //System.out.println("MEIA");
            return getShow(showID).comprarIngresso(loteID, TipoIngresso.MEIA);

        }
        if(tipo.equals("NORMAL")) {
            //System.out.println("NORMAL");
            return getShow(showID).comprarIngresso(loteID, TipoIngresso.NORMAL);

        }
        throw new IllegalArgumentException("Ingresso Esgotado");
    }

     */

    public boolean getLoteStatus(String showID, String loteID) {
        return getLote(showID, loteID).isStatus();
    }


    public void comprarLote(String show1ID, String loteID) {
        getShow(show1ID).comprarLote(loteID);
    }

    public String gerarRelatorio(String showID) {
        return getShow(showID).gerarRelatorio();
    }
}
