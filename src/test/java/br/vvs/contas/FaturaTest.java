package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Fatura;
import br.vvs.contas.model.FaturaStatusEnum;
import br.vvs.contas.model.TipoPagamentoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FaturaTest {
    private Conta conta;

    @BeforeEach
    public void setup() {
        Date data = new Date();
        Integer codigo = 1;
        double valorPago = 59.99;
        conta = new Conta(codigo, data, valorPago);

    }

    @Test
    void criarFatura() {
        Date data = new Date();
        double valorTotal = 59.99;
        String nomeCliente = "Gabriel";
        Fatura fatura = new Fatura(data, valorTotal, nomeCliente);
        assertNotNull(fatura);
        assertEquals(fatura.getData().getTime(), data.getTime());
        assertEquals(fatura.getValorTotal(), valorTotal);
        assertEquals(fatura.getNomeCliente(), nomeCliente);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);
    }

    @Test
    void setContasFatura() {
        Date data = new Date();
        double valorTotal = 59.99;
        String nomeCliente = "Gabriel";
        Fatura fatura = new Fatura(data, valorTotal, nomeCliente);
        ArrayList<Conta> contas = new ArrayList<>(Collections.singleton(conta));
        fatura.setContas(contas);

        assertEquals(1, fatura.getContas().size());
        assertEquals(conta, fatura.getContas().get(0));
    }

    @Test
    void updateFatura() {
        Date data = new Date();
        double valorTotal = 59.99;
        String nomeCliente = "Gabriel";
        Fatura fatura = new Fatura(data, valorTotal, nomeCliente);
        conta.updatePagamento(data, TipoPagamentoEnum.BOLETO);
        ArrayList<Conta> contas = new ArrayList<>(Collections.singleton(conta));
        fatura.setContas(contas);
        fatura.update();

        assertEquals(FaturaStatusEnum.PAGA, fatura.getStatus());
    }


}
