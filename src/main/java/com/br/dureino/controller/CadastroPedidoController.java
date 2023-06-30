package com.br.dureino.controller;

import com.br.dureino.model.ItemPedido;
import com.br.dureino.model.Pedido;
import com.br.dureino.model.Produto;
import com.br.dureino.model.enums.FormaPagamento;
import com.br.dureino.model.enums.StatusPedido;
import com.br.dureino.service.NegocioException;
import com.br.dureino.service.PedidoService;
import com.br.dureino.service.ProdutoService;
import com.br.dureino.util.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
@Setter
@Named
@ViewScoped
public class CadastroPedidoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pedido pedido = new Pedido();

    private ItemPedido itemPedido = new ItemPedido();

    private List<ItemPedido> itemPedidos = new ArrayList<>();

    private Produto produto = new Produto();

    @Inject
    private ProdutoService produtoService;

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
            calcular();
            salvarPedido();
            this.pedido = new Pedido();
            this.itemPedidos = new ArrayList<>();
            FacesUtil.addSucessoMessage("Pedido salvo com sucesso!");
        }catch(Exception e){
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }



    public List<Produto> pesquisarItenPedido(String nome){
        List<Produto> produtos = produtoService.listar();

        return produtos.stream().filter(p-> p.getNome().toUpperCase().contains(nome.toUpperCase())).collect(Collectors.toList());
    }

    public String calcular(){
        BigDecimal result = BigDecimal.ZERO;

        result = this.pedido.getValorFrete().subtract(pedido.getValorDesconto());

        pedido.setTotal(result);
        return pedido.getTotal();
    }


    public void getCarregarProduto() {
    ItemPedido itemPedido = itemPedidos.get(0);
        if (this.produto != null){
            itemPedido.setProduto(this.produto);
        }
//        itemPedidos.add(1,new ItemPedido());

    }

    public void salvarPedido(){
        pedido = pedidoService.salvar(pedido);
        itemPedido.setPedido(pedido);
        pedidoService.salvar(itemPedido);

    }

    public void incrementarLista(){
        ItemPedido itemPedido = this.pedido.getItemPedidos().get(0);
        itemPedidos.add(0,itemPedido);
    }


    public void inicializarLista(){

        this.itemPedidos.add(0,new ItemPedido());

    }
}
