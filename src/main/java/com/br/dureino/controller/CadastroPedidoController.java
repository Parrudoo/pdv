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

    private Produto produto = new Produto();

    @Inject
    private ProdutoService produtoService;

    @Inject
    private PedidoService pedidoService;


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
            pedidoService.salvar(pedido);
            this.pedido = new Pedido();
            FacesUtil.addSucessoMessage("Pedido salvo com sucesso!");
        }catch(Exception e){
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }



    public List<Produto> pesquisarItenPedido(String nome){
        List<Produto> produtos = produtoService.listar();
        List<Produto> produtosSugeridos  = new ArrayList<>();

        for(Produto produto : produtos){
            if (produto.getNome().toUpperCase().startsWith(nome.toUpperCase())){
                produtosSugeridos.add(produto);
            }
        }
        return produtosSugeridos;
    }

    public String calcular(){
        BigDecimal result = BigDecimal.ZERO;

            result = this.pedido.getValorFrete().subtract(pedido.getValorDesconto());


        pedido.setTotal(result);
        System.out.println("o resultado é:"+result);

        return pedido.getTotal();
    }


}
