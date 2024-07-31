package br.vvs.ingressos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketmanTest {
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
    void criarShowInvalido() {
        System.out.println("Tentar criar um Show Inválido");
        Ticketman ticketman = new Ticketman();
        String artistaVazio = "";
        int cacheInvalido = -1000;
        int despesasInvalidas = -1000;
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, art1, cacheInvalido, despesas1, false);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, art1, cache1, despesasInvalidas, false);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, artistaVazio, cache1, despesas1, false);
                });


    }

    @Test
    void criarShowNulo() {
        System.out.println("Tentar criar um Show Nulo");
        Ticketman ticketman = new Ticketman();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(null, art1, cache1, despesas1, false);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, null, cache1, despesas1, false);
                });
    }

    @Test
    void criarShowDiferente() {
        System.out.println("Criar Show Errado");
        Ticketman ticketman = new Ticketman();
        assertFalse(show1.equals(ticketman.criarShow(dataShow1, "WILLOW", cache1, despesas1, false)));
        assertEquals(ticketman.getNumeroDeShows(), 1);
    }

    @Test
    void getShowEspecifico() {
        System.out.println("Get Show Específico");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarShow(dataShow2, art2, cache2, despesas2, true);
        assertTrue(show1.equals(ticketman.getShow("22012024Chappell Roan")));
        assertTrue(show2.equals(ticketman.getShow("25122024WILLOW")));
        assertEquals(ticketman.getNumeroDeShows(), 2);
    }

    @Test
    void getShowInvalido() {
        System.out.println("Tentar pegar um Show que não existe");
        Ticketman ticketman = new Ticketman();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.getShow(null);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.getShow("NXZero");
                });
    }

    @Test
    void addLote() {
        System.out.println("Criar e Adicionar novo Lote a um Show Específico");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarShow(dataShow2, art2, cache2, despesas2, true);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0, 10)));
        assertFalse(lote2.equals((ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0, 10))));
        assertTrue(lote2.equals(ticketman.criarLote("25122024WILLOW", 20, 10, 70, 25, 10)));
    }

    @Test
    void addLoteInvalidoNegativo() {
        System.out.println("Tentar criar um lote com números negativos para ingressos, desconto e preço");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, -25, 10, 65, 0, 10);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, -10, 65, 0, 10);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, -65, 0, 10);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, 65, -1, 10);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, 65, 0, -10);
                });
    }

    @Test
    void addLoteInvalido() {
        System.out.println("Tentar criar um lote com números inválidos para ingressos, desconto e preço");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 31, 10, 70, 0, 10);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 11, 65, 0, 10);
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, 65, 26, 10);
                });
    }

    @Test
    void addLoteNulo() {
        System.out.println("Tentar criar um lote com valores nulos");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(null, 30, 10, 70, 0, 10);
                });
    }

    @Test
    void addLoteSemShow() {
        System.out.println("Tentar criar um lote em um show que não existe");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote("NXZero", 30, 10, 70, 0, 10);
                });
    }



    @Test
    void getLotes() {
        System.out.println("Pegar os lotes de um Show Específico");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0, 10);
        ticketman.criarLote("22012024Chappell Roan", 30, 10, 60, 15, 10);
        assertEquals(lotes.size(), ticketman.getLotes("22012024Chappell Roan").size());
        String loteToString = """
                22012024Chappell Roan-0 Ingressos: 100, Vip: 25, Meia: 10
                22012024Chappell Roan-1 Ingressos: 100, Vip: 30, Meia: 10
                """;
        assertEquals(ticketman.getLotesString("22012024Chappell Roan"), loteToString);
    }

    @Test
    void comprarLote() {
        System.out.println("Comprar um Lote");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0, 10);
        assertTrue(lote1.equals(ticketman.getLote(show1ID, lote1ID)));
        lote1.comprarLote();
        ticketman.comprarLote(show1ID, lote1ID);
        assertEquals(lote1.isStatus(), ticketman.getLoteStatus(show1ID, lote1ID));
    }

    /*
    @Test
    void comprarIngresso() {
        System.out.println("Comprar um Ingresso");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0, 10);
        assertTrue(ingresso1l1.equals(ticketman.comprarIngresso(show1ID, lote1ID, "VIP")));
    }
     */


    @Test
    void gerarRelatorio() {
        System.out.println("Gerar o relatório de um Show");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote(show1ID, 30, 10, 60, 0, 20);
        ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0, 10);
        ticketman.comprarLote(show1ID, lote1ID);
        String relatorio = "30 Ingressos VIP vendidos, 10 Ingressos MEIA vendidos, " +
                "60 Ingressos NORMAIS vendidos. \n" +
                "Receita Líquida: 50000; Status Financeiro: LUCRO";
        System.out.println(relatorio);
        System.out.println(ticketman.gerarRelatorio(show1ID));
        assertEquals(relatorio, ticketman.gerarRelatorio(show1ID));
    }




}