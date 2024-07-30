package br.vvs.gabrielEx2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ticketmanTest {

    @BeforeEach
    void init() {
        Calendar dataShow1 = new GregorianCalendar();
        dataShow1.set(2024, 8, 22);
        String art1 = "Chappell Roan";
        int cache1 = 1000;
        int despesas1 = 1000;
    }

    @Test
    void criarShow() {
        Ticketman ticketman = new Ticketman();
        ticketman.criarShow(dataShow1, art1, cache1, despesas1, false);
    }

}