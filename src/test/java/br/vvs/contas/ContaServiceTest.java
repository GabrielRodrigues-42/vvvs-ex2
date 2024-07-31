package br.vvs.contas;

import br.vvs.contas.model.Conta;
import br.vvs.contas.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContaServiceTest {

    @BeforeEach
    public void setup() {

    }

    @Test
    void criarContaService() {
        ContaService service = new ContaService();
        assertNotNull(service);
    }
}
