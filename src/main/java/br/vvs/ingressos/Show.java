package br.vvs.ingressos;

import java.util.ArrayList;
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
    private int vendidosVIP;
    private int vendidosMEIA;
    private int vendidosNORMAIS;
    private int vendidosTOTAL;
    private int receita;
    private String statusFinanceiro;

    public Show(Calendar data, String artista, int cache, int despesas, boolean especial) {
        this.data = data;
        this.artista = artista;
        this.especial = especial;
        gerarReceita(cache, despesas);
        String day = String.format("%02d", data.get(Calendar.DAY_OF_MONTH));
        String month = String.format("%02d", data.get(Calendar.MONTH) + 1);
        String year = String.format("%04d", data.get(Calendar.YEAR));
        this.id = day + month + year + artista;
        this.lotes = new ArrayList<>();
        this.vendidosVIP = 0;
        this.vendidosMEIA = 0;
        this.vendidosNORMAIS = 0;
        this.vendidosTOTAL = 0;

        //System.out.println(id);


    }

    private void gerarReceita(int cache, int despesas) {
        this.cache = cache*100; // Valores multiplicados por 100 para eliminar centavos;
        if(this.especial) {
            this.despesas = despesas*115;
        }
        else {
            this.despesas = despesas*100;
        }
        this.receita = 0 - (this.cache + this.despesas);
    }

    public Lote criarLote(int vip, int meia, int normal, int desconto, int precoIngresso) {
        String loteID = this.id + "-" + lotes.size();
        Lote lote = new Lote(loteID, vip, meia, normal, desconto, precoIngresso);
        lotes.add(lote);
        return lote;
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
    public Lote getLote(String loteID) {
        for(int i = 0; i < lotes.size(); i++) {
            if(lotes.get(i).getID().equals(loteID)) {
                return lotes.get(i);
            }
        }
        throw new IllegalArgumentException("Lote não existe");
    }

    public String toString() {
        return artista + id;
    }

    public String gerarRelatorio() {
        checkStatusFinanceiro();
        int mes = this.data.get(Calendar.MONTH) + 1;
        String dataFormatada = data.get(Calendar.DAY_OF_MONTH) + "/" + mes + "/" + data.get(Calendar.YEAR);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Show de ").append(artista).append(" - ").append(dataFormatada)
                .append("\n").append(this.vendidosVIP).append(" Ingressos VIP vendidos, ")
                .append(this.vendidosMEIA).append(" Ingressos MEIA vendidos, ")
                .append(this.vendidosNORMAIS).append(" Ingressos NORMAIS vendidos. \n")
                .append("Receita Líquida: ").append(this.receita)
                .append("; Status Financeiro: ").append(this.statusFinanceiro);

        return relatorio.toString();
    }


    public void comprarLote(String loteID) {
        getLote(loteID).comprarLote();
        this.vendidosVIP += getLote(loteID).getNumeroVip();
        this.vendidosMEIA += getLote(loteID).getNumeroMeia();
        this.vendidosNORMAIS += getLote(loteID).getNumeroNormal();
        receita += getLote(loteID).getValorTotal();
        this.vendidosTOTAL += getLote(loteID).getValorTotal();
    }

    public void checkStatusFinanceiro() {
        if(receita > 0) {
            this.statusFinanceiro = "LUCRO";
        }
        else {
            if(receita == 0) {
                this.statusFinanceiro = "ESTÁVEL";
            }
            else {
                this.statusFinanceiro = "PREJUÍZO";
            }
        }
    }

    /*
    public Ingresso comprarIngresso(String loteID, TipoIngresso tipo) {
        return getLote(loteID).comprarIngresso(tipo);
    }

     */
}
