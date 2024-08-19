package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Fatura;
import br.vvs.contas.model.FaturaStatusEnum;
import br.vvs.contas.model.TipoPagamentoEnum;
import br.vvs.contas.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        Date dataLimite = new Date(conta.getData().getYear(), conta.getData().getMonth(), conta.getData().getDate() - 15);
        service.pagarFatura(fatura, conta.getCodigo(), dataLimite, TipoPagamentoEnum.CARTAO_CREDITO);

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

    @Test
    void exemploI() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestI");
        Conta contaI = new Conta(1, data, 500);
        Conta contaII = new Conta(2, data, 400);
        Conta contaIII = new Conta(3, data, 600);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), data, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaII.getCodigo(), data, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaIII.getCodigo(), data, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);

    }

    @Test
    void exemploII() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date dataI = calendar.getTime();
        calendar.set(2023, 1, 17, 0, 0, 0);
        Date dataII = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestII");
        Conta contaI = new Conta(1, data, 700);
        Conta contaII = new Conta(2, data, 800);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dataI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaII.getCodigo(), dataII, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);

    }

    @Test
    void exemploIII() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();
        calendar.set(2023, 1, 6, 0, 0, 0);
        Date dataI = calendar.getTime();
        calendar.set(2023, 1, 17, 0, 0, 0);
        Date dataII = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestII");
        Conta contaI = new Conta(1, data, 700);
        Conta contaII = new Conta(2, data, 800);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dataI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaII.getCodigo(), dataII, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

    }

    @Test
    @DisplayName("Pagar por cartão")
    void casoDeTeste11() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date dataI = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestI");
        Conta contaI = new Conta(1, data, 1500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dataI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);

    }

    @Test
    @DisplayName("Pagar por boleto")
    void casoDeTeste12() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestI");
        Conta contaI = new Conta(1, data, 1500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), data, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);
    }

    @Test
    @DisplayName("Pagar por transferencia")
    void casoDeTeste13() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestI");
        Conta contaI = new Conta(1, data, 1500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), data, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);
    }

    @Test
    @DisplayName("Pagar por cartão com valor insuficiente")
    void casoDeTeste14() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date dataI = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestI");
        Conta contaI = new Conta(1, data, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dataI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

    }

    @Test
    @DisplayName("Pagar por boleto com valor insuficente")
    void casoDeTeste15() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestI");
        Conta contaI = new Conta(1, data, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), data, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);
    }

    @Test
    @DisplayName("Pagar por transferência com valor insuficiente")
    void casoDeTeste16() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date data = calendar.getTime();

        fatura = new Fatura(data, 1500 ,"TestI");
        Conta contaI = new Conta(1, data, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), data, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);
    }


}
