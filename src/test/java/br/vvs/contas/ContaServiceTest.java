package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Fatura;
import br.vvs.contas.model.FaturaStatusEnum;
import br.vvs.contas.model.TipoPagamentoEnum;
import br.vvs.contas.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContaServiceTest {

    Fatura fatura;
    Conta conta;

    @BeforeEach
    public void setup() {
        Date data = new Date();
        Integer codigo = 1;
        double valorPago = 59.99;
        conta = new Conta(codigo, data, valorPago);

        double valorTotal = 59.99;
        String nomeCliente = "Gabriel";
        fatura = new Fatura(data, valorTotal, nomeCliente);
        ArrayList<Conta> contas = new ArrayList<>(Collections.singleton(conta));
        fatura.setContas(contas);

    }

    @Test
    void criarContaService() {
        ContaService service = new ContaService();
        assertNotNull(service);
    }

    @Test
    void pagarFaturaPorCartao() {
        ContaService service = new ContaService();
        service.pagarFatura(fatura, conta.getCodigo(), conta.getData(), TipoPagamentoEnum.CARTAO_CREDITO);

        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);
    }

    @Test
    void pagarFaturaPorTransferencia() {
        ContaService service = new ContaService();
        service.pagarFatura(fatura, conta.getCodigo(), conta.getData(), TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);

        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);

    }

    @Test
    void pagarFaturaPorBoleto() {
        ContaService service = new ContaService();
        service.pagarFatura(fatura, conta.getCodigo(), conta.getData(), TipoPagamentoEnum.BOLETO);

        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);
    }


}
