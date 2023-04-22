package br.gov.cesarschool.poo.fidelidade.cartao.entidade;

import java.util.Date;

import br.gov.cesarschool.poo.fidelidade.cliente.entidade.Cliente;


public class CartaoFidelidade {

    private long numero;
    private double saldo;
    private double saldoPontos;
    private Date dataHoraAtualizacao;

    public CartaoFidelidade(long numero) {
        this.numero = numero;
        this.saldo = 0;
        this.dataHoraAtualizacao = new Date();
        this.setSaldoPontos(0);
    }
    
	public CartaoFidelidade(String numeroCartao, Cliente cliente) {
		// TODO Auto-generated constructor stub
	}

	public long getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void creditar(double valor) {
        this.saldo += valor;
        this.dataHoraAtualizacao = new Date();
    }

    public void debitar(double valor) {
        this.saldo -= valor;
        this.dataHoraAtualizacao = new Date();
    }

	public long getNumeroFidelidade() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void creditarPontos(double quantidadePontos) {
		// TODO Auto-generated method stub
		
	}

	public double getSaldoPontos() {
		return saldoPontos;
	}

	public void setSaldoPontos(double saldoPontos) {
		this.saldoPontos = saldoPontos;
	}
}
