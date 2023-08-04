package com.qbroca.bean;

import com.qbroca.entity.Pedido;
import com.qbroca.entity.Restaurante;
import com.qbroca.rn.PedidoRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class PedidoBean implements Serializable {

    private Restaurante restaurante = new Restaurante(1);
    private List<Pedido> ultimosPedidos;
    private final PedidoRN PEDIDO_RN = new PedidoRN();

    @PostConstruct
    public void init() {
        this.ultimosPedidos = PEDIDO_RN.obterUltimosPedidos(this.restaurante);
    }

    public List<Pedido> getUltimosPedidos() {
        return ultimosPedidos;
    }

    public void setUltimosPedidos(List<Pedido> ultimosPedidos) {
        this.ultimosPedidos = ultimosPedidos;
    }
}
