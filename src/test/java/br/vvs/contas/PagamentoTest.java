package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Pagamento;
import br.vvs.contas.model.TipoPagamentoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        try {
            Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
            assert(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Valor Inválido!");
        }
    }

    @Test
    @DisplayName("Pagamentos por boleto: valor mínimo")
    void casoTeste01() {
        Date data = new Date();
        double valorPago = 0.01;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getValorPago(), valorPago);
    }
    @Test
    @DisplayName("Pagamentos por boleto: valor logo acima do mínimo")
    void casoTeste02() {
        Date data = new Date();
        double valorPago = 0.02;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getValorPago(), valorPago);
    }
    @Test
    @DisplayName("Pagamentos por boleto: valor qualquer")
    void casoTeste03() {
        Date data = new Date();
        double valorPago = 500;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getValorPago(), valorPago);
    }
    @Test
    @DisplayName("Pagamentos por boleto: valor máximo")
    void casoTeste04() {
        Date data = new Date();
        double valorPago = 5000;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getValorPago(), valorPago);
    }
    @Test
    @DisplayName("Pagamentos por boleto: valor logo abaixo do máximo")
    void casoTeste05() {
        Date data = new Date();
        double valorPago = 4999;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        Pagamento pagamento = new Pagamento(valorPago, data, tipoPagamento);
        assertNotNull(pagamento);
        assertEquals(pagamento.getValorPago(), valorPago);
    }

    @Test
    @DisplayName("Pagamentos por boleto: valor logo abaixo do minimo")
    void casoTeste06() {
        Date data = new Date();
        double valorPago = 0.009;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        try {
            new Pagamento(valorPago, data, tipoPagamento);
            assert(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Valor Inválido!");
        }
    }

    @Test
    @DisplayName("Pagamentos por boleto: valor logo acima do máximo")
    void casoTeste07() {
        Date data = new Date();
        double valorPago = 5001;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        try {
            new Pagamento(valorPago, data, tipoPagamento);
            assert(false);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Valor Inválido!");
        }
    }
}
