package br.gov.cesarschool.poo.fidelidade.cartao.dao;

import java.util.List;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;

public class LancamentoExtratoDAO {
    
    public LancamentoExtratoDAO() {
    }
    
    public boolean incluir(LancamentoExtratoPontuacao pontuacao) {
        return false;
    }
    
    public boolean incluir(LancamentoExtratoResgate resgate) {
        return false;
    }
    
    public List<LancamentoExtratoPontuacao> buscarPontuacoes(long numeroCartao) {
        return null;
    }
    
    public List<LancamentoExtratoResgate> buscarResgates(long numeroCartao) {
        return null;
    }
    
}
