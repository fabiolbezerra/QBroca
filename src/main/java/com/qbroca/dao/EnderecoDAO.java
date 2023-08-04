package com.qbroca.dao;

import com.qbroca.entity.Cliente;
import com.qbroca.entity.Endereco;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EnderecoDAO extends GenericDAO<Endereco> {

    public List<Endereco> obterPorCliente(Cliente cliente) {
        List<Endereco> resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "SELECT e FROM Endereco e WHERE e.cliente = :cliente";

        try {
            TypedQuery<Endereco> query = em.createQuery(JPQL, Endereco.class);
            resposta = query.setParameter("cliente", cliente).getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

}
