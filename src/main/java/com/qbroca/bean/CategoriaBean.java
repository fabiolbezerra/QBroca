package com.qbroca.bean;

import com.qbroca.entity.Categoria;
import com.qbroca.entity.Restaurante;
import com.qbroca.rn.CategoriaRN;
import com.qbroca.util.UtilBean;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class CategoriaBean implements Serializable {

    private Restaurante restaurante = new Restaurante(1);

    private Categoria categoria;
    private Categoria categoriaSelecionada;

    private List<Categoria> categorias;
    private final CategoriaRN CATEGORIA_RN = new CategoriaRN();

    @PostConstruct
    public void init() {
        inicializarCategoria();

        this.categorias = CATEGORIA_RN.obterTodos(this.restaurante);
    }

    private void inicializarCategoria() {
        this.categoria = new Categoria();
        this.categoria.setRestaurante(this.restaurante);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void novaCategoria() {
        inicializarCategoria();
    }

    public void onRowSelect(SelectEvent event) {
        if (this.categoriaSelecionada != null) {
            this.categoria = this.categoriaSelecionada;
        }
    }

    public void salvarCategoria() {
        if (this.categoria != null) {
            if (CATEGORIA_RN.salvar(this.categoria)) {
                this.categorias = CATEGORIA_RN.obterTodos(this.restaurante);
                UtilBean.criarMensagemDeInformacao(this.categoria.getNome(), "Categoria salva com sucesso");
            } else {
                UtilBean.criarMensagemDeErro(this.categoria.getNome(), "Erro ao salvar a categoria");
            }
        } else {
            UtilBean.criarMensagemDeAviso("Nenhum categoria foi selecionada/criada");
        }
        inicializarCategoria();
    }
}
