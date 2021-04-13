package com.market.market_system.src.user;

public class Comprador extends user.User {

  private double valorAPagar;
  private int comprasRealizadas;
  private String cpf;
  
  public Comprador(String nome, double saldo, String cpf) {
    super(nome, saldo);
    this.valorAPagar = 0.0;
    this.cpf = cpf;
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
