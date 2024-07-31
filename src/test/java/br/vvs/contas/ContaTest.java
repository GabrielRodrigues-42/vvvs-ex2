package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Fatura;
import br.vvs.contas.model.Pagamento;
import br.vvs.contas.model.TipoPagamentoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContaTest {
    private Pagamento pagamento;

    @BeforeEach
    public void setup() {
        Date data = new Date();
        double valorPago = 50.00;
        TipoPagamentoEnum tipoPagamento = TipoPagamentoEnum.BOLETO;
        pagamento = new Pagamento(valorPago, data, tipoPagamento);
    }

    @Test
    void criarConta() {
        Date data = new Date();
        Integer codigo = 1;
        double valorPago = 50.00;
        Conta conta = new Conta(codigo, data, valorPago);
        assertNotNull(conta);
        assertEquals(conta.getData().getTime(), data.getTime());
        assertEquals(conta.getValorPago(), valorPago);
        assertEquals(conta.getCodigo(), codigo);
    }

    @Test
    void setPagamentoConta() {
        Date data = new Date();
        Integer codigo = 1;
        double valorPago = 50.00;
        Conta conta = new Conta(codigo, data, valorPago);
        conta.setPagamento(pagamento);

        assertEquals(pagamento, conta.getPagamento());
    }
}
