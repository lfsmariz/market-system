package com.market.market_system.util.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import store.utils.Produto;

public class Vendedor extends User {
  
  private double valorAReceber;
  private String cnpj;
  private int vendasReal;
  private Set<Produto> catalogo;
  
  public Vendedor(String nome, String cnpj) {
    super(nome, 0.0);
    this.valorAReceber = 0.0;
    this.vendasReal = 0;
    this.cnpj = cnpj;
    this.catalogo = new HashSet<>();
  }

  public void addVendaRealizada() {
    this.vendasReal += 1;
  }

  public void addCatalog(Produto prod) {
    this.catalogo.add(prod);
  }


  public void addValorAReceber(double valorAReceber) {
    this.valorAReceber += valorAReceber;
  }

  public void showCatalogo() {
    for (Produto produtos : catalogo) {
      System.out.println(produtos);
    }
  }

  public String getCnpj() {
    return cnpj;
  }

  public Set<Produto> getCatalogo() {
    return catalogo;
  }

  public Map<String, String> toJson() {
    Map tmpMap = new HashMap();
    tmpMap.put("nome", super.nome);
    tmpMap.put("saldo", super.saldo);
    tmpMap.put("cpf", this.cnpj);
    tmpMap.put("toReceive", this.valorAReceber);
    tmpMap.put("purchases", this.vendasReal);

    return tmpMap;
  }

  @Override
  public String toString() {
    String valRec= String.valueOf(this.valorAReceber);
    return super.toString() + "CNPJ: " + this.cnpj + "\n" + "Val. Receber: " + valRec + "\n" + "Vend. Real.: " + this.vendasReal ;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !other.getClass().equals(Vendedor.class)) {
      return false;
    } else {
      Vendedor otherVendedor = (Vendedor) other;
      return this.cnpj.equals(otherVendedor.cnpj);
    }
  }

  @Override
  public int hashCode() {
    return this.cnpj.hashCode();
  }
}
