package br.vvs.contas.model;

import lombok.Data;

import java.util.Date;

@Data
public class Conta {
    private final Integer codigo;
    private final Date data;
    private final double valorPago;

    public Conta(Integer codigo, Date data, double valorPago) {
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
    }
}
