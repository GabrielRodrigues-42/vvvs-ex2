package br.vvs.ingressos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketmanFunctionalAVLTests {
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
    void addLoteVIPMAX() {
        System.out.println("Criar e Adicionar novo Lote a um Show Específico");
        Ticketman ticketman = new Ticketman();
        Show show = ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 30, 10, 60, 18, 10)));
        assertTrue(show.getLote("22012024Chappell Roan-0").equals(lote1));

    }

    @Test
    void addLoteVIPMIN() {
        System.out.println("Criar e Adicionar novo Lote a um Show Específico");
        Ticketman ticketman = new Ticketman();
        Show show = ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 20, 10, 70, 10, 10)));
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
    void addLoteExcessoMEIA() {
        System.out.println("Tentar criar um lote com excesso de ingressos MEIA");
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
    void addLoteFaltaMEIA() {
        System.out.println("Tentar criar um lote com falta de ingressos MEIA");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 30, 9, 61, 10, 10);
                });
    }
    @Test
    void addLotePrecoMAX() {
        System.out.println("Tentar criar um lote com preço de ingresso inválido");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 30, 10, 60, 10, Integer.MAX_VALUE+1);
                });
    }
    @Test
    void addLotePrecoMIN() {
        System.out.println("Tentar criar um lote com preço de ingresso inválido");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 30, 10, 60, 10, -1);
                });
    }

    @Test
    void addLoteGratuito() {
        System.out.println("Criar um lote gratuito");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 10, 0)));

    }

    @Test
    void addLoteDescontoMAX() {
        System.out.println("Criar um lote com o máximo de desconto possível");
        Ticketman ticketman = new Ticketman();
        Show show = ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 25, 10)));
        assertTrue(show.getLote("22012024Chappell Roan-0").equals(lote1));

    }

    @Test
    void addLoteDescontoMIN() {
        System.out.println("Criar um lote com o mínimo de desconto possível");
        Ticketman ticketman = new Ticketman();
        Show show = ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0, 10)));
        assertTrue(show.getLote("22012024Chappell Roan-0").equals(lote1));

    }

    @Test
    void addLoteDescontoExcedente() {
        System.out.println("Tentar criar um lote com o desconto excedente");
        Ticketman ticketman = new Ticketman();
        Show show = ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, 65, 26, 10);
                });

    }

    @Test
    void addLoteDescontoNegativo() {
        System.out.println("Tentar criar um lote com um desconto inferior ao mínimo permitido.");
        Ticketman ticketman = new Ticketman();
        Show show = ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarLote(this.show1ID, 25, 10, 65, -1, 10);
                });

    }

    @Test
    void cacheNegativo() {
        System.out.println("Tentar criar um show com o cachê do artista negativo");
        Ticketman ticketman = new Ticketman();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, art1, -1, despesas1, false);
                });
    }

    @Test
    void despesasNegativas() {
        System.out.println("Tentar criar um show com as despesas de infraestrutura negativas");
        Ticketman ticketman = new Ticketman();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, art1, cache1, -1, false);
                });
    }

    @Test
    void cacheExcedente() {
        System.out.println("Tentar criar um show com as cachê acima do válido");
        Ticketman ticketman = new Ticketman();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, art1, (Integer.MAX_VALUE/2) +1, despesas1, false);
                });
    }

    @Test
    void despesasExcedentes() {
        System.out.println("Tentar criar um show com as despesas de acima do válido");
        Ticketman ticketman = new Ticketman();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketman.criarShow(dataShow1, art1, cache1, (Integer.MAX_VALUE/2) +1, false);
                });
    }



}