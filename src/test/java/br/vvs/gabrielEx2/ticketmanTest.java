package br.vvs.gabrielEx2;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ticketmanTest {


    @Test
    void criarShow() {
        Calendar dataShow1 = new GregorianCalendar();
        dataShow1.set(2024, 0, 22);
        String art1 = "Chappell Roan";
        int cache1 = 1000;
        int despesas1 = 1000;
        Ticketman ticketman = new Ticketman();
        Show show = new Show(dataShow1, art1, cache1, despesas1, false);
        assertTrue(show.equals(ticketman.criarShow(dataShow1, art1, cache1, despesas1, false)));
        assertEquals(ticketman.getNumeroDeShows(), 1);
        ticketman.criarShow(dataShow1, "WILLOW", cache1, despesas1, false);
        assertEquals(ticketman.getNumeroDeShows(), 2);
    }

    @Test
    void criarShowDiferente() {
        Calendar dataShow1 = new GregorianCalendar();
        dataShow1.set(2024, 0, 22);
        String art1 = "Chappell Roan";
        int cache1 = 1000;
        int despesas1 = 1000;
        Ticketman ticketman = new Ticketman();
        Show show = new Show(dataShow1, art1, cache1, despesas1, false);
        assertFalse(show.equals(ticketman.criarShow(dataShow1, "WILLOW", cache1, despesas1, false)));
        assertEquals(ticketman.getNumeroDeShows(), 1);
    }

    @Test
    void getShowEspecifico() {
        Calendar dataShow1 = new GregorianCalendar();
        dataShow1.set(2024, 0, 22);
        Calendar dataShow2 = new GregorianCalendar();
        dataShow2.set(2024, 11, 25);
        String art1 = "Chappell Roan";
        String art2 = "WILLOW";
        int cache1 = 1000;
        int cache2 = 2000;
        int despesas1 = 1000;
        int despesas2 = 2000;
        Ticketman ticketman = new Ticketman();
        Show show1 = new Show(dataShow1, art1, cache1, despesas1, false);
        Show show2 = new Show(dataShow2, art2, cache2, despesas2, true);
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarShow(dataShow2, art2, cache2, despesas2, true);
        assertTrue(show1.equals(ticketman.getShow("22012024Chappell Roan")));
        assertTrue(show2.equals(ticketman.getShow("25122024WILLOW")));
        assertEquals(ticketman.getNumeroDeShows(), 2);
    }

    @Test
    void addLote() {
        Calendar dataShow1 = new GregorianCalendar();
        dataShow1.set(2024, 0, 22);
        Calendar dataShow2 = new GregorianCalendar();
        dataShow2.set(2024, 11, 25);
        String art1 = "Chappell Roan";
        String art2 = "WILLOW";
        int cache1 = 1000;
        int cache2 = 2000;
        int despesas1 = 1000;
        int despesas2 = 2000;
        Ticketman ticketman = new Ticketman();
        Show show1 = new Show(dataShow1, art1, cache1, despesas1, false);
        Show show2 = new Show(dataShow2, art2, cache2, despesas2, true);
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
        ticketman.criarShow(dataShow2, art2, cache2, despesas2, true);
        Lote lote = new Lote(100, 0);
        assertTrue(lote.equals(ticketman.getShow("22012024Chappell Roan").addLote(100, 0)));
    }



}