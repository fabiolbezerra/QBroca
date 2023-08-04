package com.qbroca.rn;

import com.qbroca.dao.CategoriaDAO;
import com.qbroca.entity.Categoria;
import com.qbroca.entity.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRN {
    private final CategoriaDAO CATEGORIA_DAO = new CategoriaDAO();

    public boolean salvar(Categoria categoria) {
        if (categoria == null) {
            return false;
        } else {
            if (categoria.getId() == null || categoria.getId() == 0) {
                return CATEGORIA_DAO.criar(categoria);
            } else {
                return CATEGORIA_DAO.alterar(categoria);
            }
        }
    }

    public Categoria obter(Integer id) {
        return CATEGORIA_DAO.obter(Categoria.class, id);
    }

    public List<Categoria> obterTodos(Restaurante restaurante) {
        if (restaurante != null) {
            return CATEGORIA_DAO.obter(restaurante);
        } else {
            return null;
        }
    }


}
