package com.market.market_system.util.store;

import java.time.LocalDate;
import java.util.*;

import com.market.market_system.util.store.utils.Boletopay;
import com.market.market_system.util.store.utils.Creditopay;
import com.market.market_system.util.store.utils.Debitopay;
import com.market.market_system.util.store.utils.Pixpay;
import com.market.market_system.util.user.Comprador;
import com.market.market_system.util.user.Vendedor;
import org.springframework.stereotype.Component;
import com.market.market_system.util.store.utils.Produto;

@Component
public class Store {
  private double taxaCartao;
  private double taxaBoleto;
  private Set<Comprador> listaComprador = new HashSet<>();
  private Set<Vendedor> listaVendedor = new HashSet<>();
  private Set<Produto> listaProdutos = new HashSet<>();

  public Store() {
    this.taxaBoleto = 5.0;
    this.taxaCartao = 0.05;
  }

  public void addComprador(Comprador comp) {
    this.listaComprador.add(comp);
  }

  public void addVendedor(Vendedor vend) {
    this.listaVendedor.add(vend);
  }

  public void addProduto(Produto prod) {
    this.listaProdutos.add(prod);
  }

  public Set<Produto> getListaProdutos() {
    return listaProdutos;
  }

  public Set<Vendedor> getListaVendedor() {
    return listaVendedor;
  }

  public void pix(String cpfComprador, String cnpjVendedor, List<String> codigos) {
    Pixpay payment = new Pixpay();
    payment.pagar(this.listaComprador, this.listaVendedor, cpfComprador, cnpjVendedor, codigos, LocalDate.now());
  }

  public void debito(String cpfComprador, String cnpjVendedor, List<String> codigos) {
    Debitopay payment = new Debitopay();
    payment.pagar(this.listaComprador, this.listaVendedor, cpfComprador, cnpjVendedor, codigos, LocalDate.now());
  }

  public void credito(String cpfComprador, String cnpjVendedor, List<String> codigos) {
    Creditopay payment = new Creditopay();
    payment.pagar(this.listaComprador, this.listaVendedor, cpfComprador, cnpjVendedor, codigos, LocalDate.now());
  }
  public void boleto(String cpfComprador, String cnpjVendedor, List<String> codigos, LocalDate date) {
    Boletopay payment = new Boletopay();
    payment.pagar(this.listaComprador, this.listaVendedor, cpfComprador, cnpjVendedor, codigos, date);
  }

  public Map showVendedor(String cnpj) {
    Map tmpMessage = new HashMap();
    tmpMessage.put("message", "Vendedor não encontrado");
    for (Vendedor vendedor : listaVendedor) {
      if (vendedor.getCnpj().equals(cnpj)) {
        return vendedor.toJson();
      }
    }
    return tmpMessage;
  }

  public Map<String, List<Map<String, String>>> showVendedorProd(String cnpj) {
    List tmpProds = new ArrayList<Map<String, String>>();
    Map tmpReturn = new HashMap();
    for (Vendedor vendedor : listaVendedor) {
      if (vendedor.getCnpj().equals(cnpj)) {
        for (Produto produto : vendedor.getCatalogo()) {
          tmpProds.add(produto.toJson());
        }
      }
    }

    tmpReturn.put("produtos", tmpProds);

    return tmpReturn;
  }

  public Map<String, List<Map<String, String>>> showProdutos() {
    List tmpProds = new ArrayList<Map<String, String>>();
    Map tmpReturn = new HashMap();
    for (Produto produto : listaProdutos) {
      tmpProds.add(produto.toJson());
    }
    tmpReturn.put("produtos", tmpProds);
    return tmpReturn;
  }

  public Map<String, String> showComprador(String cpf) {
    Map tmpMessage = new HashMap();
    tmpMessage.put("message", "Comprador não encontrado");
    for (Comprador comprador : listaComprador) {
      if (comprador.getCpf().equals(cpf)) {
        return comprador.toJson();
      }
    }
    return tmpMessage;
  }

  public void showCompradores() {
    for (Comprador comprador : listaComprador) {
      System.out.println(comprador);
    }
  }

  public void showVendedores() {
    for (Vendedor vendedor : listaVendedor) {
      System.out.println(vendedor);
    }
  }
}
