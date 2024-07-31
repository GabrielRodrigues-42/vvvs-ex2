package br.vvs.gabrielEx2;

import org.junit.jupiter.api.AfterEach;
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
    private Show show2;
    private Lote lote1;
    private Lote lote2;
    private Lote lote3;
    private List<Lote> lotes;

    @BeforeEach
    public void setup() {
        System.out.println("Once Upon a Time");
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
        this.show2 = new Show(dataShow2, art2, cache2, despesas2, true);
        this.lote1 = new Lote("22012024Chappell Roan-0", 25, 10, 65, 0);
        this.lote2 = new Lote("25122024WILLOW-0", 20, 10, 70, 25);
        this.lote3 = new Lote("22012024Chappell Roan-0", 30, 10, 60, 15);
        this.lotes = new ArrayList<>();
        this.lotes.add(lote1);
        this.lotes.add(lote3);

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
    void addLote() {
        System.out.println("Criar e Adicionar novo Lote a um Show Específico");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarShow(dataShow2, art2, cache2, despesas2, true);
        assertTrue(lote1.equals(ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0)));
        assertFalse(lote2.equals((ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0))));
        assertTrue(lote2.equals(ticketman.criarLote("25122024WILLOW", 20, 10, 70, 25)));
    }

    @Test
    void getLotes() {
        System.out.println("Pegar os lotes de um Show Específico");
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarLote("22012024Chappell Roan", 25, 10, 65, 0);
        ticketman.criarLote("22012024Chappell Roan", 30, 10, 60, 15);
        assertEquals(lotes.size(), ticketman.getLotes("22012024Chappell Roan").size());
        String loteToString = lote1.toString() + " Ingressos: 100, Vip: 25, Meia: 10" + "\n" +
                lote2.toString() + " Ingressos: 100, Vip: 30, Meia: 10";
        assertEquals(ticketman.getLotesString("22012024Chappell Roan"), loteToString);
    }




}