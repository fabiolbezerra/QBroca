package com.qbroca.rn;

import com.qbroca.dao.PedidoDAO;
import com.qbroca.entity.ItemPedido;
import com.qbroca.entity.Pedido;
import com.qbroca.entity.Restaurante;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PedidoRN {

    private final PedidoDAO PEDIDO_DAO = new PedidoDAO();

    public boolean salvar(Pedido pedido) {
        if (pedido == null) {
            return false;
        } else {
            if (pedido.getId() == null || pedido.getId() == 0) {
                pedido.setDataHora(new Date());
                return PEDIDO_DAO.criar(pedido);
            } else {
                return PEDIDO_DAO.alterar(pedido);
            }
        }
    }

    public void atualizarTotal(Pedido pedido) {
        if (pedido != null && pedido.getItemPedidoList() != null) {
            BigDecimal total = new BigDecimal(0.0);
            for (ItemPedido item : pedido.getItemPedidoList()) {
                total = total.add(item.getTotal());
            }
            pedido.setTotal(total);
        }
    }

    /**
     * Retorna a lista dos últimos pedidos realizados nas últimas 24h
     *
     * @param restaurante restaurante dono dos pedidos
     * @return lista de pedidos
     */
    public List<Pedido> obterUltimosPedidos(Restaurante restaurante) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date inicio = calendar.getTime(); //Uma hora atrás
        Date fim = new Date(); //agora

        List<Pedido> resposta = PEDIDO_DAO.obterPedidos(restaurante, inicio, fim);
        return resposta;
    }

    /**
     * Retorna o número total de pedidos realizados pelo restaurante
     *
     * @param restaurante
     * @return total de pedidos
     */
    public Long obterNumeroDePedidos(Restaurante restaurante) {
        Long total = 0L;

        if (restaurante != null) {
            total = PEDIDO_DAO.obterNumeroDePedidos(restaurante);
        }

        return total;
    }

    public Long obterNumeroDePedidos24h(Restaurante restaurante) {
        Long total = 0L;

        if (restaurante != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, -24);
            Date inicio = calendar.getTime(); //Uma hora atrás
            Date fim = new Date(); //agora

            total = PEDIDO_DAO.obterNumeroDePedidos(restaurante, inicio, fim);
        }

        return total;
    }

    public Long obterNumeroDePedidosNoMes(Restaurante restaurante) {
        Long total = 0L;

        if (restaurante != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            Date inicio = calendar.getTime(); //Primeiro dia e hora do mês
            Date fim = new Date(); //agora

            total = PEDIDO_DAO.obterNumeroDePedidos(restaurante, inicio, fim);
        }

        return total;
    }

    public Double obterMediaDosPedidos(Restaurante restaurante) {
        Double media = 0.0;

        if (restaurante != null) {
            media = PEDIDO_DAO.obterMediaDosPedidos(restaurante);
        }

        return media != null ? media : 0;
    }

    public Double obterMediaDosPedidos24h(Restaurante restaurante) {
        Double media = 0.0;

        if (restaurante != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, -24);
            Date inicio = calendar.getTime(); //Uma hora atrás
            Date fim = new Date(); //agora

            media = PEDIDO_DAO.obterMediaDosPedidos(restaurante, inicio, fim);
        }

        return media != null ? media : 0;
    }

    public Double obterMediaDosPedidosNoMes(Restaurante restaurante) {
        Double total = 0.0;

        if (restaurante != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            Date inicio = calendar.getTime(); //Primeiro dia e hora do mês
            Date fim = new Date(); //agora

            total = PEDIDO_DAO.obterMediaDosPedidos(restaurante, inicio, fim);
        }

        return total;
    }
}
