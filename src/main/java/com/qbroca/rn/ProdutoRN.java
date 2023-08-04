package com.qbroca.rn;

import com.qbroca.dao.GenericDAO;
import com.qbroca.dao.ProdutoDAO;
import com.qbroca.entity.Produto;
import com.qbroca.entity.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRN {
    private final ProdutoDAO PRODUTO_DAO = new ProdutoDAO();

    public boolean salvar(Produto produto) {
        if (produto == null) {
            return false;
        } else {
            if (produto.getId() == null || produto.getId() == 0) {
                return PRODUTO_DAO.criar(produto);
            } else {
                return PRODUTO_DAO.alterar(produto);
            }
        }
    }

    public Produto obter(Integer id) {
        return PRODUTO_DAO.obter(Produto.class, id);
    }

    public List<Produto> obterTodos(Restaurante restaurante) {
        if (restaurante != null) {
            return PRODUTO_DAO.obter(restaurante);
        } else {
            return null;
        }
    }

    public List<Produto> filtrar(List<Produto> produtos, String query) {
        List<Produto> resposta = new ArrayList<>();
        if (produtos != null && query != null && !query.trim().isEmpty()) {
            String valor = null;
            for (Produto p : produtos) {
                valor = p.getNome() + " " + p.getDescricao() + " " + p.getCategoria().getNome();
                if (valor.toLowerCase().contains(query.trim().toLowerCase())) {
                    resposta.add(p);
                }
            }
        }
        return resposta;
    }

}
