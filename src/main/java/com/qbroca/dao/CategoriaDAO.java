package com.qbroca.dao;

import com.qbroca.entity.Categoria;
import com.qbroca.entity.Restaurante;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoriaDAO extends GenericDAO<Categoria> {

    /**
     * Obtém a lista de produtos do restaurante indicado
     *
     * @return Lista de pedidos
     */
    public List<Categoria> obter(Restaurante restaurante) {
        List<Categoria> resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "select c from Categoria c " +
                " where " +
                " c.restaurante = :restaurante " +
                "order by c.nome ";

        try {
            TypedQuery<Categoria> query = em.createQuery(JPQL, Categoria.class);
            resposta = query
                    .setParameter("restaurante", restaurante)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

}
