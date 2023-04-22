package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import java.time.LocalDate;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.TipoResgate;

public class CartaoFidelidadeMediator {

    private static CartaoFidelidadeMediator instance;

    private CartaoFidelidadeDAO repositorioCartao;
    private LancamentoExtratoDAO repositorioLancamento;

    private CartaoFidelidadeMediator() {
        this.repositorioCartao = new CartaoFidelidadeDAO();
        this.repositorioLancamento = new LancamentoExtratoDAO();
    }

    public static CartaoFidelidadeMediator getInstance() {
        if (instance == null) {
            instance = new CartaoFidelidadeMediator();
        }
        return instance;
    }

    public long gerarCartao(Cliente cliente) {
        LocalDate dataAtual = LocalDate.now();
        String numeroCartao = cliente.getCpf() + dataAtual.getYear() + String.format("%02d", dataAtual.getMonthValue()) + String.format("%02d", dataAtual.getDayOfMonth());
        CartaoFidelidade cartaoFidelidade = new CartaoFidelidade(numeroCartao, cliente);
        if (repositorioCartao.incluir(cartaoFidelidade)) {
            return Long.parseLong(numeroCartao);
        } else {
            return 0;
        }
    }

    public String pontuar(long numeroCartao, double quantidadePontos) {
        if (quantidadePontos <= 0) {
            return "Quantidade de pontos deve ser maior que zero.";
        }
        CartaoFidelidade cartaoFidelidade = repositorioCartao.buscar(String.valueOf(numeroCartao));
        if (cartaoFidelidade == null) {
            return "Cartão não encontrado.";
        }
        cartaoFidelidade.creditar(quantidadePontos);
        repositorioCartao.alterar(cartaoFidelidade);
        LancamentoExtratoPontuacao lancamentoPontuacao = new LancamentoExtratoPontuacao(cartaoFidelidade, quantidadePontos);
        repositorioLancamento.incluir(lancamentoPontuacao);
        return null;
    }

    public String resgatar(long numeroCartao, double quantidadePontos, TipoResgate tipo) {
    	if (quantidadePontos <= 0) {
    	return "Quantidade de pontos deve ser maior que zero.";
    	}
    	CartaoFidelidade cartaoFidelidade = repositorioCartao.buscar(String.valueOf(numeroCartao));
    	if (cartaoFidelidade == null) {
    	return "Cartão não encontrado.";
    	}
    	if (cartaoFidelidade.getSaldo() < quantidadePontos) {
    	return "Saldo insuficiente para resgate.";
    	}
    	cartaoFidelidade.debitar(quantidadePontos);
    	repositorioCartao.alterar(cartaoFidelidade);
    	LancamentoExtratoResgate lancamentoResgate = new LancamentoExtratoResgate(cartaoFidelidade, quantidadePontos, tipo);
    	repositorioLancamento.incluir(lancamentoResgate);
    	return null;
    	}
