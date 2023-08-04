package com.qbroca.dao;

import com.qbroca.entity.Cliente;
import com.qbroca.entity.Pedido;
import com.qbroca.entity.Restaurante;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PedidoDAO extends GenericDAO<Pedido> {

    /**
     * Obtém a lista de pedidos no período de tempo/datas indicado
     *
     * @param inicio data inicial
     * @param fim data final
     * @return Lista de pedidos
     */
    public List<Pedido> obterPedidos(Restaurante restaurante, Date inicio, Date fim) {
        List<Pedido> resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "select p from Pedido p " +
                " where " +
                " p.dataHora >= :inicio " +
                " and p.dataHora <= :fim " +
                " and p.restaurante = :restaurante " +
                "order by p.dataHora desc";

        try {
            TypedQuery<Pedido> query = em.createQuery(JPQL, Pedido.class);
            resposta = query
                    .setParameter("restaurante", restaurante)
                    .setParameter("inicio", inicio)
                    .setParameter("fim", fim)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

}
