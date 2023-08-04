package com.qbroca.rn;

import com.qbroca.dao.ClienteDAO;
import com.qbroca.entity.Cliente;
import com.qbroca.entity.Restaurante;
import com.qbroca.util.UtilTexto;

import java.util.Calendar;
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

    public Long obterNumeroDeClientes(Restaurante restaurante) {
        Long total = 0L;

        if (restaurante != null) {
            total = CLIENTE_DAO.obterNumeroDeClientes(restaurante);
        }

        return total;
    }

    public Long obterNumeroDeClientes24h(Restaurante restaurante) {
        Long total = 0L;

        if (restaurante != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, -24);
            Date inicio = calendar.getTime(); //Uma hora atrás
            Date fim = new Date(); //agora

            total = CLIENTE_DAO.obterNumeroDeClientes(restaurante, inicio, fim);
        }

        return total;
    }

    public Long obterNumeroDeClientesNoMes(Restaurante restaurante) {
        Long total = 0L;

        if (restaurante != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            Date inicio = calendar.getTime(); //Primeiro dia e hora do mês
            Date fim = new Date(); //agora

            total = CLIENTE_DAO.obterNumeroDeClientes(restaurante, inicio, fim);
        }
        return total;
    }
}
