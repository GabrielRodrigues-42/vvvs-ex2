package br.vvs.gabrielEx2;

public class Ingresso {
    private int id;
    private TipoIngresso tipo;
    private boolean status;


    public Ingresso(int id, TipoIngresso tipo, boolean status) {
        this.id = id;
        this.tipo = tipo;
        this.status = status;
    }
}
