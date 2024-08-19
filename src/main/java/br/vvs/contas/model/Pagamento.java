package br.vvs.contas.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Pagamento {
    private double valorPago;
    private Date data;
    private TipoPagamentoEnum tipoPagamento;

    public Pagamento(double valorPago, Date data, TipoPagamentoEnum tipoPagamento) {
        if (tipoPagamento == TipoPagamentoEnum.BOLETO & (valorPago < 0.01 | valorPago > 5000)) {
            throw new IllegalArgumentException("Valor Inválido!");
        } else {
            this.valorPago = valorPago;
            this.data = data;
            this.tipoPagamento = tipoPagamento;
        }
    }

    public Pagamento() {

        valorPago = 0;
    }


}
