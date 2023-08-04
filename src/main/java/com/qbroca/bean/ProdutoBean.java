package com.qbroca.bean;

import com.qbroca.entity.Categoria;
import com.qbroca.entity.Produto;
import com.qbroca.entity.Restaurante;
import com.qbroca.rn.CategoriaRN;
import com.qbroca.rn.ProdutoRN;
import com.qbroca.util.UtilBean;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

    private Restaurante restaurante = new Restaurante(1);

    private Produto produto;
    private Produto produtoSelecionado;

    private List<Produto> produtos;
    private List<Categoria> categorias;
    private final ProdutoRN PRODUTO_RN = new ProdutoRN();
    private final CategoriaRN CATEGORIA_RN = new CategoriaRN();

    @PostConstruct
    public void init() {
        inicializarProduto();

        this.produtos = PRODUTO_RN.obterTodos(this.restaurante);
        this.categorias = CATEGORIA_RN.obterTodos(this.restaurante);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void novoProduto() {
        inicializarProduto();
    }

    private void inicializarProduto() {
        this.produto = new Produto();
        this.produto.setRestaurante(this.restaurante);
    }

    public void onRowSelect(SelectEvent event) {
        if (this.produtoSelecionado != null) {
            this.produto = this.produtoSelecionado;
        }
    }

    public void salvarProduto() {
        if (this.produto != null) {
            if (PRODUTO_RN.salvar(this.produto)) {
                this.produtos = PRODUTO_RN.obterTodos(this.restaurante);
                UtilBean.criarMensagemDeInformacao(this.produto.getNome(), "Produto salvo com sucesso");
            } else {
                UtilBean.criarMensagemDeErro(this.produto.getNome(), "Erro ao salvar o produto");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum produto foi selecionado/criado");
        }
        inicializarProduto();
    }
}
