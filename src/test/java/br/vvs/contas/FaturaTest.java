package br.vvs.contas;

import br.vvs.contas.model.Fatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FaturaTest {

    @BeforeEach
    public void setup() {

    }

    @Test
    void criarFatura() {
        Date data = new Date();
        double valorTotal = 59.99;
        String nomeCliente = "Gabriel";
        Fatura fatura = new Fatura(data, valorTotal, nomeCliente);
        assertNotNull(fatura);
        assertEquals(fatura.getData().getTime(), data.getTime());
        assertEquals(fatura.getValorTotal(), valorTotal);
        assertEquals(fatura.getNomeCliente(), nomeCliente);
    }


}
