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
        Date dataLimite = new Date(this.data.getYear(), this.data.getMonth(), this.data.getDate() + 1);
        Date dataLimiteCartao = new Date(this.data.getYear(), this.data.getMonth(), this.data.getDate() - 14);
        for (Conta c : contas) {
            if (!(c.getPagamento().getValorPago() == 0)) {
                if (c.getPagamento().getTipoPagamento() == TipoPagamentoEnum.CARTAO_CREDITO
                        && c.getPagamento().getData().before(dataLimiteCartao)) {
                    valorPago += c.getPagamento().getValorPago();
                } else if (!(c.getPagamento().getTipoPagamento() == TipoPagamentoEnum.CARTAO_CREDITO)
                        & c.getPagamento().getData().before(dataLimite)) {
                    valorPago += c.getPagamento().getValorPago();
                }
            }
        }

        if (valorPago >= this.valorTotal) {
            setStatus(FaturaStatusEnum.PAGA);
        } else {
            setStatus(FaturaStatusEnum.PENDENTE);
        }
    }
}
