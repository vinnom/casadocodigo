package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Relatorio {

  @DateTimeFormat
  private Calendar dataGeracao = null;
  private Integer quantidade;
  private List<Produto> produtos;

  public void setRelatorio(List<Produto> produtos) {
    this.dataGeracao = Calendar.getInstance();
    this.produtos = produtos;
    this.quantidade = produtos.size();
  }

  public List<Object> getRelatorio(Calendar data) {
    List<Object> lista = new ArrayList<>();
    if (data != null) {
      lista.add("dataGeracao: " + dataGeracao.getTimeInMillis());
      lista.add("quantidade: " + quantidade);
      lista.add("produtos: " + produtos);
    } else {
      lista.add("produtos: " + produtos);
    }
    return lista;
  }

}
