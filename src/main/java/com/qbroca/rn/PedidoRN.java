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
            for (ItemPedido item: pedido.getItemPedidoList()) {
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
}
