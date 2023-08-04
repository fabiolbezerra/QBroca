package com.qbroca.bean;

import com.qbroca.entity.*;
import com.qbroca.rn.ClienteRN;
import com.qbroca.rn.EnderecoRN;
import com.qbroca.rn.PedidoRN;
import com.qbroca.rn.ProdutoRN;
import com.qbroca.util.UtilBean;
import com.qbroca.util.UtilTexto;
import com.qbroca.ws.client.ViaCepClient;
import com.qbroca.ws.dto.ViaCepDTO;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class NovoPedidoBean implements Serializable {
    private String telefone;
    private String cep;
    private double quantidade = 1.0;
    private String observacao;
    private final Restaurante restaurante = new Restaurante(1);
    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();
    private Endereco enderecoSelecionado;
    private Produto produto;
    private ItemPedido itemSelecionado;
    private Pedido pedido;
    private List<ItemPedido> itensDoPedido = new ArrayList<>();
    private List<Endereco> enderecos;
    private List<Produto> produtos;

    private final ClienteRN CLIENTE_RN = new ClienteRN();
    private final PedidoRN PEDIDO_RN = new PedidoRN();
    private final ProdutoRN PRODUTO_RN = new ProdutoRN();
    private final EnderecoRN ENDERECO_RN = new EnderecoRN();
    private final ViaCepClient VIA_CEP = new ViaCepClient();

    @PostConstruct
    public void init() {
        //Pedido inicial
        this.pedido = new Pedido();
        this.pedido.setRestaurante(this.restaurante);
        this.pedido.setCliente(this.cliente);
        this.pedido.setEndereco(this.endereco);
        this.pedido.setTotal(new BigDecimal(0.0));

        //Produtos disponíveis para o pedido
        this.produtos = PRODUTO_RN.obterTodos(this.restaurante);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ItemPedido getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(ItemPedido itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<ItemPedido> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<ItemPedido> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }

    public void pesquisarCliente() {
        this.cliente = CLIENTE_RN.obterPorTelefone(this.telefone);
        if (this.cliente != null) {
            //Mensagem de sucesso
            UtilBean.criarMensagemDeInformacao("Cliente localizado");
            this.enderecos = ENDERECO_RN.obterPorCliente(this.cliente);
        } else {
            this.cliente = new Cliente();
            this.enderecos = null;
            //Mensagem de erro
            UtilBean.criarMensagemDeAviso("Nenhum cliente encontrado");
        }
        this.pedido.setCliente(this.cliente);
    }

    public void salvarCliente() {
        //Inicializando/Atualizando valores do cliente
        this.cliente.setRestaurante(this.restaurante);
        this.cliente.setTelefone(UtilTexto.removeCharacterPhone(this.telefone));

        if (CLIENTE_RN.salvar(this.cliente)) {
            //Mensagem de sucesso
            UtilBean.criarMensagemDeInformacao("Cliente gravado no banco de dados");
        } else {
            this.cliente = new Cliente();
            this.enderecos = null;
            this.telefone = null;
            //Mensagem de erro
            UtilBean.criarMensagemDeAviso("Não foi possível salvar o cliente");
        }
    }

    public void pesquisarEnderecoPorCEP() {
        String cepSemSimbolos = UtilTexto.removeSimbolosPontuacoes(this.cep);
        ViaCepDTO viaCepDTO = VIA_CEP.pesquisarCEP(cepSemSimbolos);
        if (viaCepDTO != null) {
            this.endereco.setBairro(viaCepDTO.getBairro());
            this.endereco.setLogradouro(viaCepDTO.getLogradouro());
            this.endereco.setCidade(viaCepDTO.getLocalidade());
            this.endereco.setEstado(viaCepDTO.getUf());
        } else {
            UtilBean.criarMensagemDeAviso(this.cep, "Nenhum endereço localizado");
        }
    }

    public void onRowSelect(SelectEvent event) {
        if (this.enderecoSelecionado != null) {
            this.pedido.setEndereco(this.enderecoSelecionado);
            String mensagem = this.enderecoSelecionado.getLogradouro();
            mensagem += ", " + this.enderecoSelecionado.getNumero();
            UtilBean.criarMensagemDeInformacao("Endereço selecionado", mensagem);
        }
    }

    public void salvarEnderecoDoCliente() {
        if (this.endereco.getCliente() == null) {
            this.endereco.setCliente(this.cliente);
        }
        if (ENDERECO_RN.salvar(this.endereco)) {
            this.endereco = new Endereco();
            this.enderecos = ENDERECO_RN.obterPorCliente(this.cliente);
            //Mensagem de sucesso
            UtilBean.criarMensagemDeInformacao("Endereço adicionado ao cliente no banco de dados");
        } else {
            this.enderecos = null;
            //Mensagem de erro
            UtilBean.criarMensagemDeAviso("Não foi possível salvar o endereço");
        }
    }

    public List<Produto> autocompleteProduto(String query) {
        return PRODUTO_RN.filtrar(this.produtos, query);
    }

    public void adicionarProdutoAoPedido() {
        if (this.produto != null && this.itensDoPedido != null && this.quantidade > 0) {
            String nomeProduto = this.produto.getNome();
            //Criando e adicionando intem
            ItemPedido item = new ItemPedido();
            item.setPedido(this.pedido);
            item.setProduto(this.produto);
            item.setQuantidade(this.quantidade);
            item.setTotal(new BigDecimal(this.quantidade).multiply(this.produto.getPreco()));
            item.setObservacao(this.observacao);
            this.itensDoPedido.add(item);

            //Limpando
            inicializandoNovoItem();

            //Atualizando total do pedido
            this.pedido.setItemPedidoList(this.itensDoPedido);
            PEDIDO_RN.atualizarTotal(this.pedido);

            //Mensagem de sucesso
            UtilBean.criarMensagemDeInformacao(nomeProduto, "Produto adicionado ao pedido");
        } else {
            //Mensagem de erro
            UtilBean.criarMensagemDeAviso("Nenhum produto selecionado ou quantidade igual a zero");
        }
    }

    public void excluirItemDoPedido() {
        if (this.itemSelecionado != null) {
            int indice = 0;
            for (ItemPedido itemPedido : this.itensDoPedido) {
                if (itemPedido.getProduto().equals(this.itemSelecionado.getProduto())
                        && itemPedido.getQuantidade() == this.itemSelecionado.getQuantidade()
                        && itemPedido.getObservacao() == this.itemSelecionado.getObservacao()) {
                    this.itensDoPedido.remove(indice);
                    break;
                }
                indice++;
            }

            inicializandoNovoItem();

            //Atualizando total do pedido
            this.pedido.setItemPedidoList(this.itensDoPedido);
            PEDIDO_RN.atualizarTotal(this.pedido);

            //Mensagem de sucesso
            UtilBean.criarMensagemDeInformacao("Item removido do pedido");
        } else {
            //Mensagem de erro
            UtilBean.criarMensagemDeAviso("Nenhum item selecionado para remoção");
        }
    }

    private void inicializandoNovoItem() {
        //Limpando
        this.produto = null;
        this.quantidade = 1.0;
        this.observacao = null;
    }

    public String criarPedido() {
        if (PEDIDO_RN.salvar(this.pedido)) {
            String sumario = "Pedido: " + this.pedido.getId();
            String detalhes = "Novo pedido criado. Total: " + UtilTexto.formatarValorMoeda(this.pedido.getTotal());
            UtilBean.criarMensagemDeInformacao(sumario, detalhes);
            return "lista.xhtml";
        } else {
            UtilBean.criarMensagemDeErro("Pedido", "Erro ao criar o pedido");
            return null;
        }
    }
}
