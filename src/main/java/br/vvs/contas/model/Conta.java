package br.vvs.contas.model;

import lombok.Data;

import java.util.Date;

@Data
public class Conta {
    private Integer codigo;
    private Date data;
    private double valorPago;
    private Pagamento pagamento;

    public Conta(Integer codigo, Date data, double valorPago) {
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
    }
}
