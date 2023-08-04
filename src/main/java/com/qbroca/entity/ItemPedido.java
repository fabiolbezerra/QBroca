/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qbroca.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author flbez
 */
@Entity
@Table(name = "item_pedido")
@NamedQueries({
        @NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i"),
        @NamedQuery(name = "ItemPedido.findById", query = "SELECT i FROM ItemPedido i WHERE i.id = :id"),
        @NamedQuery(name = "ItemPedido.findByQuantidade", query = "SELECT i FROM ItemPedido i WHERE i.quantidade = :quantidade"),
        @NamedQuery(name = "ItemPedido.findByTotal", query = "SELECT i FROM ItemPedido i WHERE i.total = :total")})
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private double quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Size(max = 50)
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "pedido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "produto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(Integer id) {
        this.id = id;
    }

    public ItemPedido(Integer id, double quantidade, BigDecimal total) {
        this.id = id;
        this.quantidade = quantidade;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qbroca.entity.ItemPedido[ id=" + id + " ]";
    }

}
