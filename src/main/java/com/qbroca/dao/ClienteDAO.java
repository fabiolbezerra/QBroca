package com.qbroca.dao;

import com.qbroca.entity.Cliente;
import com.qbroca.entity.Endereco;
import com.qbroca.entity.Restaurante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClienteDAO extends GenericDAO<Cliente> {

    public Cliente obterPorTelefone(String telefone) {
        Cliente resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "select c from Cliente c where c.telefone = :telefone";

        try {
            TypedQuery<Cliente> query = em.createQuery(JPQL, Cliente.class);
            List<Cliente> clientes = query.setParameter("telefone", telefone).getResultList();
            if (clientes != null && !clientes.isEmpty()) {
                resposta = clientes.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

    public Long obterNumeroDeClientes(Restaurante restaurante) {
        Long resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "select count(c) from Cliente c where c.restaurante = :restaurante";

        try {
            Query query = em.createQuery(JPQL);
            query.setParameter("restaurante", restaurante);
            resposta = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

    public Long obterNumeroDeClientes(Restaurante restaurante, Date inicio, Date fim) {
        Long resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "select count(c) from Cliente c where c.restaurante = :restaurante and c.dataHora >= :inicio and c.dataHora <= :fim";

        try {
            Query query = em.createQuery(JPQL);
            query
                    .setParameter("restaurante", restaurante)
                    .setParameter("inicio", inicio)
                    .setParameter("fim", fim);
            resposta = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

}
