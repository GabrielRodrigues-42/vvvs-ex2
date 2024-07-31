package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Pagamento;
import br.vvs.contas.model.TipoPagamentoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PagamentoTest {

    @BeforeEach
    public void setup() {

    }

    @Test
    void criarPagamentoBoleto() {
        Date data = new Date();
        double valorPago = 50.00;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getData().getTime(), data.getTime());
        assertEquals(pagamento.getValorPago(), valorPago);
        assertEquals(pagamento.getTipoPagamento(), tipoPagamento);
    }

    @Test
    void criarPagamentoCartao() {
        Date data = new Date();
        double valorPago = 50.00;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.CARTAO_CREDITO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getData().getTime(), data.getTime());
        assertEquals(pagamento.getValorPago(), valorPago);
        assertEquals(pagamento.getTipoPagamento(), tipoPagamento);
    }

    @Test
    void criarPagamentoTransferencia() {
        Date data = new Date();
        double valorPago = 50.00;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.TRANSFERENCIA_BANCARIA;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getData().getTime(), data.getTime());
        assertEquals(pagamento.getValorPago(), valorPago);
        assertEquals(pagamento.getTipoPagamento(), tipoPagamento);
    }

    @Test
    void criarPagamentoBoletoComValorInvalido() {
        Date data = new Date();
        double valorPago = 0.001;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getValorPago(), 0);
    }
}
