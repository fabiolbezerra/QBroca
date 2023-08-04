package com.qbroca.bean;

import com.qbroca.entity.Restaurante;
import com.qbroca.rn.ClienteRN;
import com.qbroca.rn.PedidoRN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class DashboardBean implements Serializable {

    private Long numeroDeClientes24h;
    private Long numeroDeClientesNoMes;
    private Long numeroDeClientes;
    private Long numeroDePedidos24h;
    private Long numeroDePedidosNoMes;
    private Long numeroDePedidos;
    private Double mediaDosPedidos24h;
    private Double mediaDosPedidosNoMes;
    private Double mediaDosPedidos;
    private Restaurante restaurante = new Restaurante(1);

    private final PedidoRN PEDIDO_RN = new PedidoRN();
    private final ClienteRN CLIENTE_RN = new ClienteRN();

    @PostConstruct
    public void init() {
        //Dashboard Cliente
        this.numeroDeClientes24h = CLIENTE_RN.obterNumeroDeClientes24h(this.restaurante);
        this.numeroDeClientesNoMes = CLIENTE_RN.obterNumeroDeClientesNoMes(this.restaurante);
        this.numeroDeClientes = CLIENTE_RN.obterNumeroDeClientes(this.restaurante);

        //Dashboard Pedido
        this.numeroDePedidos24h = PEDIDO_RN.obterNumeroDePedidos24h(this.restaurante);
        this.numeroDePedidosNoMes = PEDIDO_RN.obterNumeroDePedidosNoMes(this.restaurante);
        this.numeroDePedidos = PEDIDO_RN.obterNumeroDePedidos(this.restaurante);

        this.mediaDosPedidos24h = PEDIDO_RN.obterMediaDosPedidos24h(this.restaurante);
        this.mediaDosPedidosNoMes = PEDIDO_RN.obterMediaDosPedidosNoMes(this.restaurante);
        this.mediaDosPedidos = PEDIDO_RN.obterMediaDosPedidos(this.restaurante);
    }

    public Long getNumeroDeClientes24h() {
        return numeroDeClientes24h;
    }

    public Long getNumeroDeClientesNoMes() {
        return numeroDeClientesNoMes;
    }

    public Long getNumeroDeClientes() {
        return numeroDeClientes;
    }

    public Long getNumeroDePedidos24h() {
        return numeroDePedidos24h;
    }

    public Long getNumeroDePedidosNoMes() {
        return numeroDePedidosNoMes;
    }

    public Long getNumeroDePedidos() {
        return numeroDePedidos;
    }

    public Double getMediaDosPedidos24h() {
        return mediaDosPedidos24h;
    }

    public Double getMediaDosPedidosNoMes() {
        return mediaDosPedidosNoMes;
    }

    public Double getMediaDosPedidos() {
        return mediaDosPedidos;
    }
}
