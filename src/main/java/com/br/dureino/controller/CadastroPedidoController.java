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

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
import java.util.concurrent.ExecutionException;
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

    private List<ItemPedido> listaDeItenAseremVendidos = new ArrayList<>();

    private BigDecimal valorProdutos = BigDecimal.ZERO;


    private static List<ItemPedido> teste = new ArrayList<>();

    private  List<ItemPedido> produtosGuardados = new ArrayList<>();

    private List<ItemPedido> produtosGuardadosSelecionados;

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


    public void getCarregarProduto(){
    BigDecimal total = BigDecimal.ZERO;

    ItemPedido itemPedido = new ItemPedido();
    itemPedido.setQtd(1);

    boolean adiciona = false;
    if (!produtosGuardados.isEmpty()){
        for (ItemPedido pedido : produtosGuardados){
                if (this.itemPedido.getProduto().getId().equals(pedido.getProduto().getId()))
                    adiciona = true;
        }
    }
         if (adiciona){
             FacesUtil.addErrorMessage("produto já existente na lista!");
         }else{
             itemPedido.setProduto(this.itemPedido.getProduto());

             this.produtosGuardados.add(itemPedido);
         }


        this.itemPedido.setProduto(null);
        recalcular();
    }

    public BigDecimal recalcular(){
        BigDecimal result = BigDecimal.ZERO;
        result = result.add(this.pedido.getValorFrete().subtract(pedido.getValorDesconto()));

        this.pedido.setTotal(result);

        return this.pedido.getTotal();
    }






    public List<ItemPedido> adicionarAlista(){
        ItemPedido pedido = new ItemPedido();
        for (ItemPedido itemPedido : produtosGuardadosSelecionados){
        pedido.setProduto(itemPedido.getProduto());
//            ItemPedido pedido = new ItemPedido(itemPedido.getId(),
//                    itemPedido.getQtd(),
//                    itemPedido.getValorTotal(),
//                    itemPedido.getProduto(),
//                    itemPedido.getPedido());

            this.listaDeItenAseremVendidos.add(pedido);
        }

        return listaDeItenAseremVendidos;
    }




    public void salvarPedido(){
        pedido = pedidoService.salvar(pedido);

        for (ItemPedido itemPedido : produtosGuardados){
            itemPedido.setPedido(pedido);
            pedidoService.salvar(itemPedido);
        }

    }



}
