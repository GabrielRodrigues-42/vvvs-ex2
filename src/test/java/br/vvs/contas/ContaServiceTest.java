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
        Date date = new Date();
        Integer codigo = 1;
        double valorPago = 59.99;
        conta = new Conta(codigo, date, valorPago);

        double valorTotal = 59.99;
        String nomeCliente = "Gabriel";
        fatura = new Fatura(date, valorTotal, nomeCliente);
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
        Date dateLimite = new Date(conta.getData().getYear(), conta.getData().getMonth(), conta.getData().getDate() - 15);
        service.pagarFatura(fatura, conta.getCodigo(), dateLimite, TipoPagamentoEnum.CARTAO_CREDITO);

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
        Date date = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestI");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 400);
        Conta contaIII = new Conta(3, date, 600);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaII.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaIII.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);

    }

    @Test
    void exemploII() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date dateI = calendar.getTime();
        calendar.set(2023, 1, 17, 0, 0, 0);
        Date dateII = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 700);
        Conta contaII = new Conta(2, date, 800);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dateI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaII.getCodigo(), dateII, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);

    }

    @Test
    void exemploIII() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 6, 0, 0, 0);
        Date dateI = calendar.getTime();
        calendar.set(2023, 1, 17, 0, 0, 0);
        Date dateII = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 700);
        Conta contaII = new Conta(2, date, 800);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dateI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

        service.pagarFatura(fatura, contaII.getCodigo(), dateII, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

    }

    @Test
    @DisplayName("Pagar por cartão")
    void casoDeTeste11() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date dateI = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestI");
        Conta contaI = new Conta(1, date, 1500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dateI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);

    }

    @Test
    @DisplayName("Pagar por boleto")
    void casoDeTeste12() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestI");
        Conta contaI = new Conta(1, date, 1500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);
    }

    @Test
    @DisplayName("Pagar por transferencia")
    void casoDeTeste13() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestI");
        Conta contaI = new Conta(1, date, 1500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PAGA);
    }

    @Test
    @DisplayName("Pagar por cartão com valor insuficiente")
    void casoDeTeste14() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date dateI = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestI");
        Conta contaI = new Conta(1, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), dateI, TipoPagamentoEnum.CARTAO_CREDITO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);

    }

    @Test
    @DisplayName("Pagar por boleto com valor insuficente")
    void casoDeTeste15() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestI");
        Conta contaI = new Conta(1, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);
    }

    @Test
    @DisplayName("Pagar por transferência com valor insuficiente")
    void casoDeTeste16() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestI");
        Conta contaI = new Conta(1, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(fatura.getStatus(), FaturaStatusEnum.PENDENTE);
    }

    @Test
    void regra1() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), creditLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), date, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PAGA, fatura.getStatus());
    }

    @Test
    void regra2() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), creditLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PENDENTE, fatura.getStatus());
    }

    @Test
    void regra3() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), date, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PENDENTE, fatura.getStatus());
    }

    @Test
    void regra4() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), date, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PENDENTE, fatura.getStatus());
    }

    @Test
    void regra5() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), creditLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), date, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PENDENTE, fatura.getStatus());
    }

    @Test
    void regra6() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), creditLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PENDENTE, fatura.getStatus());
    }

    @Test
    void regra7() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), date, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PENDENTE, fatura.getStatus());
    }

    @Test
    void regra8() {
        ContaService service = new ContaService();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 1, 20, 0, 0, 0);
        Date date = calendar.getTime();
        calendar.set(2023, 1, 5, 0, 0, 0);
        Date creditLimit = calendar.getTime();
        calendar.set(2023, 1, 21, 0, 0, 0);
        Date defaultAboveLimit = calendar.getTime();

        fatura = new Fatura(date, 1500 ,"TestII");
        Conta contaI = new Conta(1, date, 500);
        Conta contaII = new Conta(2, date, 500);
        Conta contaIII = new Conta(3, date, 500);
        ArrayList<Conta> contas = new ArrayList<>(List.of(new Conta[]{contaI, contaII, contaIII}));
        fatura.setContas(contas);

        service.pagarFatura(fatura, contaI.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.BOLETO);
        service.pagarFatura(fatura, contaII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.CARTAO_CREDITO);
        service.pagarFatura(fatura, contaIII.getCodigo(), defaultAboveLimit, TipoPagamentoEnum.TRANSFERENCIA_BANCARIA);
        assertEquals(FaturaStatusEnum.PENDENTE, fatura.getStatus());
    }


}
