package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.time.LocalDateTime;
import java.util.Date;

public class LancamentoExtratoResgate extends LancamentoExtratoDAO {
    private TipoResgate tipoResgate;

    public LancamentoExtratoResgate(long numeroCartao, int quantidadePontos, LocalDateTime dataHoraLancamento, TipoResgate tipoResgate) {
        super(numeroCartao, quantidadePontos, dataHoraLancamento);
        this.tipoResgate = tipoResgate;
    }

    public TipoResgate getTipoResgate() {
        return tipoResgate;
    }

	public void setNumeroCartaoFidelidade(long numero) {
		// TODO Auto-generated method stub
		
	}

	public void setData(Date date) {
		// TODO Auto-generated method stub
		
	}
}
