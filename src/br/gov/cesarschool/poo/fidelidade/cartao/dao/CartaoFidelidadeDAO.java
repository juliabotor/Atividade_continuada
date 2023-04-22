package br.gov.cesarschool.poo.fidelidade.cartao.dao;

import br.gov.cesarschool.poo.fidelidade.cartao.entidade.CartaoFidelidade;

import java.io.*;

public class CartaoFidelidadeDAO {
    private final String NOME_ARQUIVO = "cartoes.dat";

    public boolean incluir(CartaoFidelidade cartao) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO, true));
            out.writeObject(cartao);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(CartaoFidelidade cartao) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO));
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO + ".tmp"));

            boolean encontrado = false;
            while (!encontrado && in.available() > 0) {
                CartaoFidelidade c = (CartaoFidelidade) in.readObject();
                if (c.getNumero() == cartao.getNumero()) {
                    out.writeObject(cartao);
                    encontrado = true;
                } else {
                    out.writeObject(c);
                }
            }

            in.close();
            out.close();

            if (encontrado) {
                File antigo = new File(NOME_ARQUIVO);
                antigo.delete();
                File novo = new File(NOME_ARQUIVO + ".tmp");
                novo.renameTo(antigo);
                return true;
            } else {
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CartaoFidelidade buscar(long numero) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO));
            CartaoFidelidade cartao = null;
            boolean encontrado = false;
            while (!encontrado && in.available() > 0) {
                CartaoFidelidade c = (CartaoFidelidade) in.readObject();
                if (c.getNumero() == numero) {
                    cartao = c;
                    encontrado = true;
                }
            }
            in.close();
            return cartao;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
