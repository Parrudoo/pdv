package com.br.dureino.controller;

import com.br.dureino.dto.DetalhamentoPedidoDTO;
import com.br.dureino.model.*;
import com.br.dureino.model.enums.FormaPagamento;
import com.br.dureino.model.enums.StatusPedido;
import com.br.dureino.security.Seguranca;
import com.br.dureino.service.NegocioException;
import com.br.dureino.service.PedidoService;
import com.br.dureino.service.ProdutoService;
import com.br.dureino.util.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.text.html.parser.Parser;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Getter
@Setter
@Named
@ViewScoped
public class CadastroPedidoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pedido pedido = new Pedido();

    private Pedido pedidoEdicao;

    private EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

    private ItemPedido produtoSelecionado = new ItemPedido();

    private ItemPedido itemPedido = new ItemPedido();

    private List<ItemPedido> itemPedidos = new ArrayList<>();

    private ItemPedido prodCarrinhoSelecionado;

    private Produto produto = new Produto();

    private List<ItemPedido> listaDeItenAseremVendidos = new ArrayList<>();

    private BigDecimal valorProdutos = BigDecimal.ZERO;


    private static List<ItemPedido> teste = new ArrayList<>();

    private  List<ItemPedido> produtosGuardados = new ArrayList<>();

    private List<ItemPedido> produtosGuardadosSelecionados;

    @Inject
    private ProdutoService produtoService;

    @Inject
    private Seguranca seguranca;

    @Inject
    private PedidoService pedidoService;

    private ItemPedido carregarProduto;



    public List<SelectItem> getStatus(){
          List<SelectItem> selectItems = new ArrayList<>();
          for(StatusPedido pedido : StatusPedido.values()){
              Object value = pedido;
              String label = pedido.getDescricao();
              selectItems.add(new SelectItem(value,label));
          }
          return selectItems;
    }

    public List<SelectItem> getUsuarioLogado(){
        List<SelectItem> user = new ArrayList<>();



        String label = seguranca.getUsuario().getEmail();

        user.add(new SelectItem(null,label));

        return user;
    }

    public List<SelectItem> getFormaPagamento(){
        List<SelectItem> items = new ArrayList<>();
        for(FormaPagamento pagamento : FormaPagamento.values()){
            Object value = pagamento;
            String label = pagamento.getDescricao();
            items.add(new SelectItem(value,label));
        }
        return  items;
    }

    public void salvar(){
        try {
            salvarPedido();
            this.pedido = new Pedido();
            this.itemPedidos = new ArrayList<>();
            this.produtosGuardados = new ArrayList<>();
            FacesUtil.addSucessoMessage("Pedido."+pedido.getNumero()+" "+"salvo com sucesso!");
        }catch(Exception e){
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }



    public List<Produto> pesquisarItenPedido(String nome){
        List<Produto> produtos = produtoService.listar();

        return produtos.stream().filter(p-> p.getNome().toUpperCase().contains(nome.toUpperCase())).collect(Collectors.toList());
    }

    public List<Produto> pesquisarItenPedidoCodigo(Long id){
        List<Produto> produtos = produtoService.listar();
        List<Produto> list = new ArrayList<>();

       for (Produto produto : produtos){
           if (id.equals(produto.getId())){
               produto.setNome(produto.getId().toString().concat(" - ").concat(produto.getNome()));
               list.add(produto);
           }
       }
        return  list;
    }


    public void getCarregarProduto(){
    BigDecimal total = BigDecimal.ZERO;
    ItemPedido itemPedido = new ItemPedido();

    boolean adiciona = false;


    if (!pedido.getItemPedidos().isEmpty()){

        for (ItemPedido pedido : pedido.getItemPedidos()){
                if (this.itemPedido.getProduto().getId().equals(pedido.getProduto().getId())){
                    pedido.setQtd(pedido.getQtd()+1);
                    adiciona = true;
                }

        }
    }
         if (adiciona){
             FacesUtil.addErrorMessage("produto incrementado na lista!");
         }else{

             itemPedido.setProduto(this.itemPedido.getProduto());


             itemPedido.getProduto().setItemPedidos(pedido.getItemPedidos());


             this.pedido.getItemPedidos().add(itemPedido);
             this.pedido.setTotal(pedido.getTotal());
         }


        this.itemPedido.setProduto(null);

//        recalcular();
    }


    public void remover(){
        this.produtosGuardados.remove(prodCarrinhoSelecionado);
    }


    public BigDecimal recalcular(){

        return pedido.getTotal();
    }





    public boolean editarPedido(){
        if (pedido.getId() != null){
            return true;
        }
        return false;
    }


    public void salvarPedido(){

        List<ItemPedido> itemPedidos = new ArrayList<>();
        Produto produto = null;
        if (!pedido.getItemPedidos().isEmpty()){
            detalharPedido();
            for (ItemPedido itemPedido : pedido.getItemPedidos()){
                ItemPedido pedidao = new ItemPedido(itemPedido.getId(),
                        itemPedido.getQtd(),
                        itemPedido.getValorTotal(),
                        itemPedido.getProduto(),
                        pedido);
                itemPedidos.add(pedidao);

                this.pedido.setTotal(pedido.getTotal());
                this.pedido.setItemPedidos(itemPedidos);


//                atualizar produto
                if (itemPedido.getProduto().getEstoque() != null) {
                    produto = new Produto(itemPedido.getProduto().getId(),
                            itemPedido.getProduto().getSku(),
                            itemPedido.getProduto().getNome(),
                            itemPedido.getProduto().getSubCategoria(),
                            itemPedido.getProduto().getValorUnitario(),
                            itemPedido.getProduto().getPrecoCusto(),
                            itemPedido.getProduto().getNcm(),
                            itemPedido.getProduto().getLucro(),
                            itemPedido.getProduto().getEstoque(),
                            itemPedido.getProduto().getValorMinimo(),
                            itemPedido.getProduto().getValorMaximo(),
                            itemPedido.getProduto().getEstoqueMinimo(),
                            itemPedido.getProduto().getEstoqueMaximo(),
                            itemPedido.getProduto().getControlerEstoque(),
                            itemPedido.getProduto().getHabilitado(),
                            itemPedido.getProduto().getUnd(),
                            null,
                            itemPedido.getProduto().getCategoria());

                produtoService.salvarProduto(produto);
                }
            }

            EnderecoEntrega enderecoEntrega = new EnderecoEntrega(pedido.getEnderecoEntrega().getId(),pedido.getEnderecoEntrega().getDataEntrega(),
                        pedido.getEnderecoEntrega().getLogradouro(),
                        pedido.getEnderecoEntrega().getComplemento(),
                        pedido.getEnderecoEntrega().getCidade(),
                        pedido.getEnderecoEntrega().getNumero(),
                        pedido.getEnderecoEntrega().getCep(),
                        pedido);



            Usuario usuario = new Usuario();
            usuario.setNome(seguranca.getUsuario().getNome());
            usuario.setEmail(seguranca.getUsuario().getEmail());
            usuario.setSenha(seguranca.getUsuario().getSenha());
            usuario.setId(seguranca.getUsuario().getId());

            pedido.setUsuario(usuario);

            pedido.setEnderecoEntrega(enderecoEntrega);
            this.pedido = pedidoService.salvar(pedido);
        }


    }

    @PostConstruct
    public void init(){
        if (verificarEdicaoPedido()){

        }
    }

    public void carregarDadosDodetalhamento(String xml) {

        Pedido pedido = new Pedido();
        if (xml != null){


        DetalhamentoPedidoDTO parser = new DetalhamentoPedidoDTO(xml);




        List<ItemPedido> itemPedidos = new ArrayList<>();
        ItemPedido itemPedido = new ItemPedido();
        Produto produto = new Produto();


        Usuario usuario = new Usuario();

        usuario.setNome(parser.getAsString("USUARIO"));
        pedido.setTotal(parser.getAsBigDecimal("VALOR_TOTAL_PEDIDO"));
        itemPedido.setValorTotal(parser.getAsBigDecimal("VL_TOTAL"));
        produto.setEstoque(parser.getAsInteger("QUANTIDADE_PRODUTO"));
        produto.setNome(parser.getAsString("NOME_PRODUTO"));

        itemPedido.setProduto(produto);

        itemPedidos.add(itemPedido);

        pedido.setItemPedidos(itemPedidos);
        }

        this.pedido = pedido;

    }



    public boolean verificarEdicaoPedido(){
        if (pedido != null && pedido.getDetalhamentoPedido() != null){
         configEdicaoPedido(pedido.getDetalhamentoPedido());
           return true;

        }
        return false;
    }

    private void configEdicaoPedido(String detalhamentoPedido) {

        carregarDadosDodetalhamento(detalhamentoPedido);
    }


    public void detalharPedido(){
        DetalhamentoPedidoDTO dto = new DetalhamentoPedidoDTO();
                dto.addParametro("USUARIO",pedido.getUsuario(),false,false,true)
                .addParametro("STATUS",pedido.getStatus(),false,false,true)
                .addParametro("VALOR_TOTAL_PEDIDO",pedido.getTotal(),false,false,true);
                        for (ItemPedido itemPedido : pedido.getItemPedidos()){
                            dto.addParametro("ITEM",itemPedido.getValorTotal(),true,false,false)
                            .addParametro("PRODUTO_NOME",itemPedido.getProduto().getNome(),false,true,false)
                            .addParametro("QUANTIDADE_PRODUTO",itemPedido.getProduto().getEstoque(),false,true,false)
                            .addParametro("LUCRO",itemPedido.getProduto().getLucro(),false,true,false);
                            dto.addLista(itemPedido);

                        }

                pedido.setDetalhamentoPedido(dto.toXML());

        }


    public void removerProduto() {
        this.pedido.getItemPedidos().remove(produtoSelecionado);
    }

}
