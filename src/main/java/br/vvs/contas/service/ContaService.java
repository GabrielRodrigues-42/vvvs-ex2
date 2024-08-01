package br.vvs.contas.service;

import br.vvs.contas.model.Conta;
import br.vvs.contas.model.Fatura;
import br.vvs.contas.model.TipoPagamentoEnum;

import java.util.Date;

public class ContaService {


    public void pagarFatura(Fatura fatura, Integer codigo, Date data, TipoPagamentoEnum tipoPagamentoEnum) {
        Conta conta = null;
        for (Conta c : fatura.getContas()) {
            if (c.getCodigo() == codigo) {
                conta = c;
            }
        }
        if (conta != null){
            conta.updatePagamento(data, tipoPagamentoEnum);
            fatura.update();
        }

    }
}
