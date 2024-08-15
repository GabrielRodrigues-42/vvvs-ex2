package br.vvs.ingressos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketmanTableTests {
    private Calendar dataShow1;
    private Calendar dataShow2;
    private String art1;
    private String art2;
    private int cache1;
    private int cache2;
    private int despesas1;
    private int despesas2;
    //private Ticketman ticketman;
    private Show show1;
    private String show1ID;
    private String show2ID;
    private Show show2;
    private Lote lote1;
    private String lote1ID;
    private Lote lote2;
    private String lote2ID;
    private Lote lote3;
    private String lote3ID;
    private List<Lote> lotes;
    private Ingresso ingresso1l1;

    @BeforeEach
    public void setup() {
        this.dataShow1 = new GregorianCalendar();
        dataShow1.set(2024, 0, 22);
        this.dataShow2 = new GregorianCalendar();
        dataShow2.set(2024, 11, 25);
        this.art1 = "Chappell Roan";
        this.art2 = "WILLOW";
        this.cache1 = 1000;
        this.cache2 = 2000;
        this.despesas1 = 1000;
        this.despesas2 = 2000;
        this.show1 = new Show(dataShow1, art1, cache1, despesas1, false);
        this.show1ID = "22012024Chappell Roan";
        this.show2 = new Show(dataShow2, art2, cache2, despesas2, true);
        this.show2ID = "25122024WILLOW";
        this.lote1 = new Lote("22012024Chappell Roan-0", 25, 10, 65, 0, 10);
        this.lote1ID = "22012024Chappell Roan-0";
        this.lote2 = new Lote("25122024WILLOW-0", 20, 10, 70, 25, 10);
        this.lote2ID = "25122024WILLOW-0";
        this.lote3 = new Lote("22012024Chappell Roan-1", 30, 10, 60, 15, 10);
        this.lote3ID = "22012024Chappell Roan-1";
        this.lotes = new ArrayList<>();
        this.lotes.add(lote1);
        this.lotes.add(lote3);
        this.ingresso1l1 = new Ingresso("22012024Chappell Roan-0-1", TipoIngresso.VIP, false);

    }

    @Test
    void criarShow() {
        System.out.println("Criar Show");
        Ticketman ticketman = new Ticketman();
        assertTrue(show1.equals(ticketman.criarShow(dataShow1, art1, cache1, despesas1, false)));
        assertEquals(ticketman.getNumeroDeShows(), 1);
        ticketman.criarShow(dataShow1, "WILLOW", cache1, despesas1, false);
        assertEquals(ticketman.getNumeroDeShows(), 2);
    }

    @Test
    void addLote() {
        System.out.println("Criar e Adicionar novo Lote a um Show Específico");
        Ticketman ticketman = new Ticketman();
        Show show = ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 18, 10)));
        assertTrue(show.getLote("22012024Chappell Roan-0").equals(lote1));

    }

    @Test
    void addLoteExcessoVIP() {
        System.out.println("Tentar criar um lote com excesso de ingressos VIP");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 31, 10, 60, 10, 10);
                });
    }

    @Test
    void addLoteMEIAInvalido() {
        System.out.println("Tentar criar um lote com faixa de ingressos MEIA inválida");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 30, 11, 60, 10, 10);
                });
    }

    @Test
    void addLoteFaltaVIP() {
        System.out.println("Tentar criar um lote com falta de ingressos VIP");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 19, 10, 71, 10, 10);
                });
    }

    @Test
    void addLotePrecoInvalido() {
        System.out.println("Tentar criar um lote com o preço inválido");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, 65, 10, -1);
                });
    }

    @Test
    void addLoteDescontoInvalido() {
        System.out.println("Tentar criar um lote com o preço inválido");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, 65, 26, 10);
                });
    }

    @Test
    void showDataEspecial() {
        System.out.println("Ver se as funcionalidades de Data Especial funcionam para o caso TRUE");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, true);
        ticketman.criarLote(show1ID, 30, 10, 60, 0, 48);
        ticketman.comprarLote(show1ID, lote1ID);
        String relatorio = "Show de Chappell Roan - 22/1/2024\n" +
                "30 Ingressos VIP vendidos, 10 Ingressos MEIA vendidos, " +
                "60 Ingressos NORMAIS vendidos. \n" +
                "Receita Líquida: 35000; Status Financeiro: LUCRO";
        System.out.println(relatorio);
        System.out.println(ticketman.gerarRelatorio(show1ID));
        assertEquals(relatorio, ticketman.gerarRelatorio(show1ID));
    }

    @Test
    void showDataNormal() {
        System.out.println("Ver se as funcionalidades de Data Especial funcionam para o caso FALSE");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote(show1ID, 30, 10, 60, 0, 20);
        ticketman.comprarLote(show1ID, lote1ID);
        String relatorio = "Show de Chappell Roan - 22/1/2024\n" +
                "30 Ingressos VIP vendidos, 10 Ingressos MEIA vendidos, " +
                "60 Ingressos NORMAIS vendidos. \n" +
                "Receita Líquida: 50000; Status Financeiro: LUCRO";
        System.out.println(relatorio);
        System.out.println(ticketman.gerarRelatorio(show1ID));
        assertEquals(relatorio, ticketman.gerarRelatorio(show1ID));
    }

    @Test
    void showLUCRO() {
        System.out.println("Ver se o relatório corretamente aponta lucro");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, 1000, 2000, false);
        ticketman.criarLote(show1ID, 30, 10, 60, 0, 48);
        ticketman.comprarLote(show1ID, lote1ID);
        String relatorio = "Show de Chappell Roan - 22/1/2024\n" +
                "30 Ingressos VIP vendidos, 10 Ingressos MEIA vendidos, " +
                "60 Ingressos NORMAIS vendidos. \n" +
                "Receita Líquida: 300000; Status Financeiro: LUCRO";
        System.out.println(relatorio);
        System.out.println(ticketman.gerarRelatorio(show1ID));
        assertEquals(relatorio, ticketman.gerarRelatorio(show1ID));
    }

    @Test
    void showESTAVEL() {
        System.out.println("Ver se o relatório corretamente aponta estabilidade");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, 1500, false);
        ticketman.criarLote(show1ID, 30, 10, 60, 0, 20);
        ticketman.comprarLote(show1ID, lote1ID);
        String relatorio = "Show de Chappell Roan - 22/1/2024\n" +
                "30 Ingressos VIP vendidos, 10 Ingressos MEIA vendidos, " +
                "60 Ingressos NORMAIS vendidos. \n" +
                "Receita Líquida: 0; Status Financeiro: ESTÁVEL";
        System.out.println(relatorio);
        System.out.println(ticketman.gerarRelatorio(show1ID));
        assertEquals(relatorio, ticketman.gerarRelatorio(show1ID));
    }
    @Test
    void showPREJUIZO() {
        System.out.println("Ver se o relatório corretamente aponta prejuízo");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote(show1ID, 30, 10, 60, 0, 10);
        ticketman.comprarLote(show1ID, lote1ID);
        String relatorio = "Show de Chappell Roan - 22/1/2024\n" +
                "30 Ingressos VIP vendidos, 10 Ingressos MEIA vendidos, " +
                "60 Ingressos NORMAIS vendidos. \n" +
                "Receita Líquida: -75000; Status Financeiro: PREJUÍZO";
        System.out.println(relatorio);
        System.out.println(ticketman.gerarRelatorio(show1ID));
        assertEquals(relatorio, ticketman.gerarRelatorio(show1ID));
    }

    @Test
    void showDesconto() {
        System.out.println("Ver se a receita se altera ao adicionar desconto no lote");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote(show1ID, 30, 10, 60, 10, 20);
        ticketman.comprarLote(show1ID, lote1ID);
        String relatorio = "Show de Chappell Roan - 22/1/2024\n" +
                "30 Ingressos VIP vendidos, 10 Ingressos MEIA vendidos, " +
                "60 Ingressos NORMAIS vendidos. \n" +
                "Receita Líquida: 26000; Status Financeiro: LUCRO";
        System.out.println(relatorio);
        System.out.println(ticketman.gerarRelatorio(show1ID));
        assertEquals(relatorio, ticketman.gerarRelatorio(show1ID));
    }




}