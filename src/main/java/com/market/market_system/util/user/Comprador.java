package com.market.market_system.util.user;

import java.util.HashMap;
import java.util.Map;

public class Comprador extends User{

  private double valorAPagar;
  private int comprasRealizadas;
  private String cpf;
  
  public Comprador(String nome, double saldo, String cpf) {
    super(nome, saldo);
    this.valorAPagar = 0.0;
    this.cpf = cpf;
    this.comprasRealizadas = 0;
  }

  public Comprador() {
    super("tmpComprador", 1000);
    this.valorAPagar = 0.0;
    this.cpf = "0000000";
    this.comprasRealizadas = 0;
  }

  public String getCpf() {
    return cpf;
  }

  public void addCompraRealizada() {
    this.comprasRealizadas += 1;
  }

  public void addValorAPagar(double valorAPagar) {
    this.valorAPagar += valorAPagar;
  }

  @Override
  public String toString() {
    String valPag = String.valueOf(this.valorAPagar);
    String comp = String.valueOf(this.comprasRealizadas);
    return super.toString() + "CPF: "+ this.cpf + "\n" +
    "Valor a Pagar: " + valPag + "\n" +
    "Comp. Reali.: " + comp;
  }

  public Map<String, String> toJson() {
    Map tmpMap = new HashMap();
    tmpMap.put("nome", super.nome);
    tmpMap.put("saldo", super.saldo);
    tmpMap.put("cpf", this.cpf);
    tmpMap.put("toPay", this.valorAPagar);
    tmpMap.put("purchases", this.comprasRealizadas);

    return tmpMap;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !other.getClass().equals(Comprador.class)) {
      return false;
    } else {
      Comprador otherComprador = (Comprador) other;
      return this.cpf.equals(otherComprador.cpf);
    }
  }

  @Override
  public int hashCode() {
    return this.cpf.hashCode();
  }
}
