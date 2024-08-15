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
        this.pagamento = new Pagamento();
    }

    public void updatePagamento(Date data, TipoPagamentoEnum tipoPagamentoEnum) {
        if (tipoPagamentoEnum == TipoPagamentoEnum.BOLETO) {
            updatePagamentoBoleto(data);
        } else {
            this.pagamento = new Pagamento(this.valorPago, data, tipoPagamentoEnum);
        }
    }

    private void updatePagamentoBoleto(Date data) {
        Pagamento pagamento;
        if (data.after(this.data)) {
            pagamento = new Pagamento(this.valorPago * 1.1, data, TipoPagamentoEnum.BOLETO);
        } else {
            pagamento = new Pagamento(this.valorPago, data, TipoPagamentoEnum.BOLETO);
        }
        this.pagamento = pagamento;
    }
}
