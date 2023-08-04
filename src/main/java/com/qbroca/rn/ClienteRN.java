package com.qbroca.rn;

import com.qbroca.dao.ClienteDAO;
import com.qbroca.entity.Cliente;
import com.qbroca.util.UtilTexto;

import java.util.Date;

public class ClienteRN {
    private final ClienteDAO CLIENTE_DAO = new ClienteDAO();

    public boolean salvar(Cliente cliente) {
        if (cliente == null) {
            return false;
        } else {
            if (cliente.getId() == null || cliente.getId() == 0) {
                cliente.setDataHora(new Date());
                return CLIENTE_DAO.criar(cliente);
            } else {
                return CLIENTE_DAO.alterar(cliente);
            }
        }
    }

    public Cliente obterPorTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            return null;
        } else {
            telefone = UtilTexto.removeCharacterPhone(telefone);
            return CLIENTE_DAO.obterPorTelefone(telefone);
        }
    }
}
