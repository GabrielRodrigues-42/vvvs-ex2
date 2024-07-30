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
        dataShow1.set(2024, 8, 22);
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
        dataShow1.set(2024, 8, 22);
        String art1 = "Chappell Roan";
        int cache1 = 1000;
        int despesas1 = 1000;
        Ticketman ticketman = new Ticketman();
        Show show = new Show(dataShow1, art1, cache1, despesas1, false);
        assertFalse(show.equals(ticketman.criarShow(dataShow1, "WILLOW", cache1, despesas1, false)));
        assertEquals(ticketman.getNumeroDeShows(), 1);
    }

}