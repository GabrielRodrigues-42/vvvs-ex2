package br.vvs.contas.model;

import lombok.Data;

import java.util.Date;

@Data
public class Fatura {
    private Date data;
    private double valorTotal;
    private String nomeCliente;

    public Fatura(Date data, double valorTotal, String nomeCliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
    }
}
