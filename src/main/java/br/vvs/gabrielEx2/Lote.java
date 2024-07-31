package br.vvs.gabrielEx2;

import java.util.ArrayList;
import java.util.List;

public class Lote {
    public boolean status;
    private String id;
    private int numeroDeIngressos;
    private int numeroVip;
    private int numeroMeia;
    private int precoIngresso;
    private int valorTotal;


    private int numeroNormal;
    private List<Ingresso> ingressos;
    private int desconto;

    public Lote(String id, int vip, int meia, int normal, int desconto, int precoIngresso) {
        this.id = id;
        this.status = false;
        this.ingressos = new ArrayList<>();
        this.desconto = desconto;
        this.numeroVip = vip;
        this.numeroMeia = meia;
        this.numeroNormal = normal;
        this.precoIngresso = precoIngresso*100; //valor multiplicado por 100 para eliminar centavos
        this.valorTotal = (this.precoIngresso*normal) + (this.precoIngresso*vip*2) + (this.precoIngresso*meia/2);
        numeroDeIngressos = vip+meia+normal;
        if(vip > numeroDeIngressos*0.3 || vip < numeroDeIngressos*0.2 ||
                meia > numeroDeIngressos*0.1 || meia < numeroDeIngressos*0.1) {
            throw new IllegalArgumentException("Distribuição de Ingressos Inválida");
        }
        int index = 0;
        String ingressoID = id + "-" + index;
        for(int i = 0; i < vip; i++) {
            index++;
            ingressoID = id + "-" + index;
            ingressos.add(new Ingresso(TipoIngresso.VIP, ingressoID));
        }
        for(int i = 0; i < meia; i++) {
            index++;
            ingressoID = id + "-" + index;
            ingressos.add(new Ingresso(TipoIngresso.MEIA, ingressoID));
        }
        for(int i = 0; i < normal; i++) {
            index++;
            ingressoID = id + "-" + index;
            ingressos.add(new Ingresso(TipoIngresso.NORMAL, ingressoID));
        }
        this.numeroDeIngressos = ingressos.size();

    }

    public void comprarLote() {
        for(int i = 0; i < ingressos.size(); i++) {
            ingressos.get(i).comprar();
        }
        setStatus(true);
    }

    public boolean equals(Lote lote) {
        if (lote.getId().equals(this.id)) {
            return true;
        }
        return false;
    }
    public String getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumeroDeIngressos() {
        return numeroDeIngressos;
    }

    public int getNumeroVip() {
        return numeroVip;
    }

    public int getNumeroMeia() {
        return numeroMeia;
    }

    public int getNumeroNormal() {
        return numeroNormal;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public int getDesconto() {
        return desconto;
    }

    public String toString() {
        String str = id + " Ingressos: " + numeroDeIngressos;
        String distribuicaoIngressos = ", Vip: " + numeroVip + ", Meia: " + numeroMeia;
        str = str + distribuicaoIngressos;
        return str;
    }

    public String getID() {
        return id;
    }

    /*
    public Ingresso comprarIngresso(TipoIngresso tipo) {
        for(int i = 0; i < ingressos.size(); i++) {
            if(ingressos.get(i).getTipo().equals(tipo) && ingressos.get(i).isStatus() == false) {
                return ingressos.get(i).comprar();

            }
        }
        throw new IllegalArgumentException("Ingresso Esgotado ou Indisponível");
    }

     */

    public int getValorTotal() {
        return valorTotal;
    }
}
