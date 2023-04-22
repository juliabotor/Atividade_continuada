package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.time.LocalDateTime;

public class LancamentoExtratoDAO {
    private long numeroCartao;
    private int quantidadePontos;
    private LocalDateTime dataHoraLancamento;

    public LancamentoExtratoDAO(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento) {
        this.numeroCartao = numeroCartao;
        this.quantidadePontos = quantidadePontos;
        this.dataHoraLancamento = dataHoraLancamento;
    }

    public long getNumeroCartao() {
        return numeroCartao;
    }

    public int getQuantidadePontos() {
        return quantidadePontos;
    }

    public LocalDateTime getDataHoraLancamento() {
        return dataHoraLancamento;
    }
}

