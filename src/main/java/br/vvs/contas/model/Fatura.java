package br.vvs.contas.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Fatura {
    private Date data;
    private double valorTotal;
    private String nomeCliente;
    private FaturaStatusEnum status;
    private ArrayList<Conta> contas;

    public Fatura(Date data, double valorTotal, String nomeCliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.status = FaturaStatusEnum.PENDENTE;
    }

    public void update() {
        double valorPago = 0;
        for (Conta c : contas) {
            valorPago += c.getPagamento().getValorPago();
        }
        if (valorPago >= this.valorTotal) {
            setStatus(FaturaStatusEnum.PAGA);
        } else {
            setStatus(FaturaStatusEnum.PENDENTE);
        }
    }
}
