package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pedido<E> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private BigDecimal valor;
  @DateTimeFormat
  private Calendar data;
  @OneToMany
  private List<Produto> produtos = new ArrayList<Produto>();

  public Integer getId() {
    return id;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public Calendar getData() {
    return data;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public void setData(Calendar data) {
    this.data = data;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }

  public List<String> getTituloProdutos() {
    List<String> titulos = new ArrayList<>();
    
    for (Produto produto : this.produtos) {
      titulos.add(produto.getTitulo());
    }
    
    return titulos;
  }
}
