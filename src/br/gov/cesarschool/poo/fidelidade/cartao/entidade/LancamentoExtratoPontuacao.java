package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.time.LocalDateTime;

public class LancamentoExtratoPontuacao extends LancamentoExtratoDAO {
    public LancamentoExtratoPontuacao(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento) {
        super(numeroCartao, quantidadePontos, dataHoraLancamento);
    }
}
