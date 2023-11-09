package com.br.dureino.dto;

import com.br.dureino.model.ItemPedido;
import lombok.Getter;
import lombok.Setter;
import org.jfree.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
public class DetalhamentoPedidoDTO {

    private Map<String, String> parametros;
    private Map<String, String> itens;
    private List<EtapaDTO> etapas;
    private Map<String, String> prod;

    private List<ItemPedido> lista = new ArrayList<>();
    private String detalhamento;

    public DetalhamentoPedidoDTO() {
        super();
        this.parametros = new HashMap<>();
        this.etapas = new ArrayList<>();
        this.itens = new HashMap<>();
        this.prod = new HashMap<>();
        this.lista = new ArrayList<>();

    }

    public DetalhamentoPedidoDTO(String xml){
        this();
        this.detalhamento = xml;
        parse();

    }

    public DetalhamentoPedidoDTO addParametro(String nome, Object valor,boolean itens, boolean prod,boolean p) {
        if(valor == null) {
            valor = "";
        }

        if (itens){
            this.itens.put(nome, valor.toString());
        }else if (prod){
            this.prod.put(nome, valor.toString());
        }else if (p){
            this.parametros.put(nome, valor.toString());
        }

        return this;
    }


    public DetalhamentoPedidoDTO addLista(ItemPedido itemPedido){
            lista.add(itemPedido);
            return this;

    }


    public void parse(String detalhamento){
        this.detalhamento = detalhamento;
        parse();

    }

    protected void parse() {
        if(detalhamento != null) {
            return;
        }

        Document document = Jsoup.parse(detalhamento);
        Elements pedido = document.select("parametros param");

//        Elements item = document.select("parametros item");
        parametros = new HashMap<>();
//        itens = new HashMap<>();

        for (Element el : pedido) {
            parametros.put(el.attr("nome"), el.attr("valor"));
        }

//        for (Element el : item){
//            itens.put(el.attr("nome"), el.attr("valor"));
        }



    public String getAsString(String nome) {
        if(parametros != null) {
            return parametros.get(nome);
        }

        return null;
    }
    public Integer getAsInteger(String nome) {
        if(parametros != null && parametros.containsKey(nome)) {
            String valor = parametros.get(nome);

            if(valor != null) {
                return Integer.valueOf(valor);
            } else {
                return null;
            }
        }

        return null;
    }


    public Long getAsLong(String nome) {
        if(parametros != null && parametros.containsKey(nome)) {
            String valor = parametros.get(nome);

            if(valor != null) {
                return Long.valueOf(valor);
            } else {
                return null;
            }
        }

        return null;
    }

    public BigDecimal getAsBigDecimal(String nome) {
        if(parametros != null && parametros.containsKey(nome)) {
            String valor = parametros.get(nome);

            if(valor != null) {
                return new BigDecimal(valor);
            } else {
                return null;
            }
        }

        return null;
    }

    public EtapaBuilder addEtapa(String nome) {
        EtapaDTO etapa = new EtapaDTO(nome);
        etapas.add(etapa);
        return new EtapaBuilder(etapa);
    }

    public String toXML() {
        String xml = null;

        if(isValid()) {
            xml = gerarXML();
        }

        return xml;
    }

    private String gerarXML() {
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
        .append("<pedido>\n")
            .append("\t<parametros>\n");
                for (Map.Entry<String, String> p : parametros.entrySet()) {
                    builder.append(String.format("\t\t<param nome=\"%s\" valor=\"%s\" />\n", p.getKey(), p.getValue()));
                }
            builder.append("\t</parametros>\n");

        builder.append("\t<itens>\n");
            for (int i = 0; i < lista.size(); i++) {

                String count = String.valueOf(i+1);
                builder.append(String.format("\t<item id=\"%s\">\n", count));


                        builder.append(String.format("\t\t<param nome=\"%s\" valor=\"%s\" />\n","VL_TOTAL",lista.get(i).getValorTotal()));
                        builder.append(String.format("\t\t<param nome=\"%s\" valor=\"%s\" />\n","QTD",lista.get(i).getQtd()));


                    builder.append(String.format("\t\t<prod id=\"%s\">\n",count));
                        builder.append(String.format("\t\t\t<param nome=\"%s\" valor=\"%s\" />\n","NOME_PRODUTO",lista.get(i).getProduto().getNome()));
                        builder.append(String.format("\t\t\t<param nome=\"%s\" valor=\"%s\" />\n","QUANTIDADE_PRODUTO",lista.get(i).getQtd()));
                        builder.append(String.format("\t\t\t<param nome=\"%s\" valor=\"%s\" />\n","ESTOQUE",lista.get(i).getProduto().getEstoque()));
                        builder.append(String.format("\t\t\t<param nome=\"%s\" valor=\"%s\" />\n","VL_UNITARIO",lista.get(i).getProduto().getValorUnitario()));
                        builder.append(String.format("\t\t\t<param nome=\"%s\" valor=\"%s\" />\n","SKU",lista.get(i).getProduto().getSku()));


                    builder.append(String.format("\t\t</prod>\n"));
                builder.append(String.format("\t</item>\n"));

            }

        builder.append("\t</itens>\n");
        builder.append("</pedido>\n");

        return builder.toString();
    }

    private boolean isValid() {
        return verificarParametrosNaoDefinidos();
    }

    private boolean verificarParametrosNaoDefinidos() {
        Set<String> parametrosConhecidos = new HashSet<>();
        parametrosConhecidos.addAll(parametros.keySet());
        boolean ok = true;

        for (EtapaDTO etapa : etapas) {
            String[] variaveis = etapa.getExpressao().getVariaveis();

            for (String v : variaveis) {
                ok = ok && parametrosConhecidos.contains(v);

                if(!ok) {
                    String msg = new StringBuilder("O parâmetro \"")
                            .append(v)
                            .append("\" foi incluído na etapa \"")
                            .append(etapa.getNome())
                            .append("\" no entanto não foi definido anteriormente.")
                            .toString();
                    throw new IllegalArgumentException(msg);
                }
            }

            parametrosConhecidos.add(etapa.getExpressao().getParametro());

            for (String v : variaveis) {
                parametrosConhecidos.add(v);
            }
        }

        return ok;
    }

}
