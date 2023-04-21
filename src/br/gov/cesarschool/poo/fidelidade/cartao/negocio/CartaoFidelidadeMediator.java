package br.gov.cesarschool.poo.fidelidade.cartao.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.gov.cesarschool.poo.fidelidade.cartao.dao.CartaoFidelidadeDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.dao.LancamentoExtratoDAO;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoPontuacao;
import br.gov.cesarschool.poo.fidelidade.cartao.entidade.LancamentoExtratoResgate;
import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;
import br.gov.cesarschool.poo.fidelidade.util.TipoResgate;

public class CartaoFidelidadeMediator {
	
	private static CartaoFidelidadeMediator instance;
	private CartaoFidelidadeDAO repositorioCartao;
	private LancamentoExtratoDAO repositorioLancamento;
	
	private CartaoFidelidadeMediator() {
		repositorioCartao = new CartaoFidelidadeDAO();
		repositorioLancamento = new LancamentoExtratoDAO();
	}
	
	public static CartaoFidelidadeMediator getInstance() {
		if (instance == null) {
			instance = new CartaoFidelidadeMediator();
		}
		return instance;
	}
	
	public long gerarCartao(Cliente cliente) {
		LocalDate dataAtual = LocalDate.now();
		String dataFormatada = dataAtual.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String numeroCartao = cliente.getCpf() + dataFormatada;
		CartaoFidelidade cartao = new CartaoFidelidade(numeroCartao, cliente);
		boolean sucesso = repositorioCartao.incluir(cartao);
		if (sucesso) {
			return cartao.getNumeroFidelidade();
		} else {
			return 0;
		}
	}
	
	public String pontuar(long numeroCartao, double quantidadePontos) {
		if (quantidadePontos <= 0) {
			return "Quantidade de pontos inválida";
		}
		CartaoFidelidade cartao = repositorioCartao.buscar(numeroCartao);
		if (cartao == null) {
			return "Cartão não encontrado";
		}
		cartao.creditarPontos(quantidadePontos);
		boolean sucesso = repositorioCartao.alterar(cartao);
		if (!sucesso) {
			return "Erro ao atualizar o cartão no repositório";
		}
		LancamentoExtratoPontuacao lancamento = new LancamentoExtratoPontuacao(cartao, quantidadePontos);
		sucesso = repositorioLancamento.incluir(lancamento);
		if (!sucesso) {
			return "Erro ao incluir o lançamento no extrato";
		}
		return null;
	}
	
	public String resgatar(long numeroCartao, double quantidadePontos, TipoResgate tipo) {
		if (quantidadePontos <= 0) {
			return "Quantidade de pontos inválida";
		}
		CartaoFidelidade cartao = repositorioCartao.buscar(numeroCartao);
		if (cartao == null) {
			return "Cartão não encontrado";
		}
		if (cartao.getSaldoPontos() < quantidadePontos) {
