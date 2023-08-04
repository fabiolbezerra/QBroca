package com.qbroca.dao;

import com.qbroca.entity.Cliente;
import com.qbroca.entity.Endereco;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO extends GenericDAO<Cliente> {

    public Cliente obterPorTelefone(String telefone) {
        Cliente resposta = null;

        EntityManager em = getEntityManager();
        final String JPQL = "select c from Cliente c where c.telefone = :telefone";

        try {
            TypedQuery<Cliente> query = em.createQuery(JPQL, Cliente.class);
            List<Cliente> clientes = query.setParameter("telefone", telefone).getResultList();
            if (clientes != null && ! clientes.isEmpty()) {
                resposta = clientes.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return resposta;
    }

}
