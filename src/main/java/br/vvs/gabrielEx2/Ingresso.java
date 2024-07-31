package br.vvs.gabrielEx2;

public class Ingresso {
    private String id;
    private TipoIngresso tipo;
    private boolean status;


    public Ingresso(String id, TipoIngresso tipo, boolean status) {
        this.id = id;
        this.tipo = tipo;
        this.status = status;
    }

    public Ingresso(TipoIngresso tipo, String id) {
        this.tipo = tipo;
        this.status = false;
        this.id = id;
    }

    public boolean equals(Ingresso ing) {
        if(ing.getId().equals(this.id)) {
            return true;
        }
        return false;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    public boolean isStatus() {
        return status;
    }

    public Ingresso comprar() {
        this.status = true;
        System.out.println("Ingresso Comprado");
        return this;
    }

    public String getId() {
        return id;
    }
}
