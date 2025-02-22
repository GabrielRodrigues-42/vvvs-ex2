package br.vvs.ingressos;

import java.util.ArrayList;
import java.util.List;

public class Lote {
    public boolean status;
    private String id;
    private int numeroDeIngressos;
    private int numeroVip;
    private int numeroMeia;
    private int precoIngresso;
    private int precoIngressoDescontado;
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
        this.precoIngressoDescontado = precoIngresso*(100-desconto); //valor multiplicado por 100 para eliminar centavos
        this.valorTotal = ((this.precoIngressoDescontado*normal) + (this.precoIngressoDescontado*vip*2) + (this.precoIngresso*meia/2));
        numeroDeIngressos = vip+meia+normal;
        verificarEntradas(vip, meia, normal, desconto, precoIngresso);
        adicionarIngressos();
        this.numeroDeIngressos = ingressos.size();

    }

    //Verifica se as entradas são aceitaveis para os parâmetros do projeto.
    private void verificarEntradas(int vip, int meia, int normal, int desconto, int precoIngresso) {
        if(vip > numeroDeIngressos*0.3 || vip < numeroDeIngressos*0.2 ||
                meia > numeroDeIngressos*0.1 || meia < numeroDeIngressos*0.1) {
            throw new IllegalArgumentException("Distribuição de Ingressos Inválida");
        }
        if(desconto > 25) {
            throw new IllegalArgumentException("Desconto Inválido");
        }
    }

    //Cria os ingressos do Lote.
    private void adicionarIngressos() {
        int index = 0;
        String ingressoID = id + "-" + index;
        for(int i = 0; i < this.numeroVip; i++) {
            index++;
            ingressoID = id + "-" + index;
            ingressos.add(new Ingresso(TipoIngresso.VIP, ingressoID));
        }
        for(int i = 0; i < this.numeroMeia; i++) {
            index++;
            ingressoID = id + "-" + index;
            ingressos.add(new Ingresso(TipoIngresso.MEIA, ingressoID));
        }
        for(int i = 0; i < this.numeroNormal; i++) {
            index++;
            ingressoID = id + "-" + index;
            ingressos.add(new Ingresso(TipoIngresso.NORMAL, ingressoID));
        }
    }

    //Compra todos os ingressos e muda o status para true, simbolizando a compra.
    public void comprarLote() {
        for(int i = 0; i < ingressos.size(); i++) {
            ingressos.get(i).comprar();
        }
        setStatus(true);
    }

    //Compara dois Lotes e retorna true caso tenham o mesmo ID.
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

    //Cria a representação em String de um Lote.
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
