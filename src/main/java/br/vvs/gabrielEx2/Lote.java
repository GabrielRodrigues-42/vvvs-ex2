package br.vvs.gabrielEx2;

import java.util.ArrayList;
import java.util.List;

public class Lote {
    private String id;
    private int numeroDeIngressos;
    private int numeroVip;
    private int numeroMeia;
    private int numeroNormal;
    private List<Ingresso> ingressos;
    private int desconto;

    public Lote(String id, int vip, int meia, int normal, int desconto) {
        this.id = id;
        this.ingressos = new ArrayList<>();
        this.desconto = desconto;
        this.numeroVip = vip;
        this.numeroMeia = meia;
        this.numeroNormal = normal;
        numeroDeIngressos = vip+meia+normal;
        if(vip > numeroDeIngressos*0.3 || vip < numeroDeIngressos*0.2 ||
                meia > numeroDeIngressos*0.1 || meia < numeroDeIngressos*0.1) {
            throw new IllegalArgumentException("Distribuição de Ingressos Inválida");
        }
        for(int i = 0; i < vip; i++) {
            ingressos.add(new Ingresso("VIP"));
        }
        for(int i = 0; i < meia; i++) {
            ingressos.add(new Ingresso("MEIA"));
        }
        for(int i = 0; i < normal; i++) {
            ingressos.add(new Ingresso("normal"));
        }
        this.numeroDeIngressos = ingressos.size();

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

}
