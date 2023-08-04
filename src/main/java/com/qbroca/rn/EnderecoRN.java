package com.qbroca.rn;

import com.qbroca.dao.EnderecoDAO;
import com.qbroca.entity.Cliente;
import com.qbroca.entity.Endereco;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;

public class EnderecoRN {
    private final EnderecoDAO ENDERECO_DAO = new EnderecoDAO();

    public boolean salvar(Endereco endereco) {
        if (endereco == null) {
            return false;
        } else {
            if (endereco.getId() == null || endereco.getId() == 0) {
                return ENDERECO_DAO.criar(endereco);
            } else {
                return ENDERECO_DAO.alterar(endereco);
            }
        }
    }

    public List<Endereco> obterPorCliente(Cliente cliente) {
        if (cliente == null) {
            return null;
        } else {
            return ENDERECO_DAO.obterPorCliente(cliente);
        }
    }
}
