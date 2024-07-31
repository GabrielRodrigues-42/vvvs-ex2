package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Fatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContaTest {

    @BeforeEach
    public void setup() {

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
}
