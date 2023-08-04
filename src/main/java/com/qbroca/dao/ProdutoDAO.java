package com.qbroca.dao;

import com.qbroca.entity.Produto;
import com.qbroca.entity.Restaurante;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class ProdutoDAO extends GenericDAO<Produto> {

    /**
     * Obtém a lista de produtos do restaurante indicado
     *
     * @return Lista de pedidos
     */
    public List<Produto> obter(Restaurante restaurante) {
        List<Produto> resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "select p from Produto p " +
                " where " +
                " p.restaurante = :restaurante " +
                "order by p.nome ";

        try {
            TypedQuery<Produto> query = em.createQuery(JPQL, Produto.class);
            resposta = query
                    .setParameter("restaurante", restaurante)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

}
