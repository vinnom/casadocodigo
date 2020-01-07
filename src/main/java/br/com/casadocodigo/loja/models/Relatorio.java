package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Relatorio  {

  @DateTimeFormat
  private Calendar dataGeracao = null;
  private Integer quantidade;
  private List<Produto> produtos;
  
  public Relatorio() {
  }
  
  public Relatorio(List<Produto> produtos) {
    this.dataGeracao = Calendar.getInstance();
    this.produtos = produtos;
    this.quantidade = produtos.size();
  }

  public Calendar getDataGeracao() {
    return dataGeracao;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

}
