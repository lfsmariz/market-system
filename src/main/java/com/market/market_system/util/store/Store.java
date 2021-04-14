package com.market.market_system.util.store;

import java.time.LocalDate;
import java.util.*;

import com.market.market_system.util.user.Comprador;
import com.market.market_system.util.user.Vendedor;
import org.springframework.stereotype.Component;
import store.utils.Produto;

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
    Comprador compradorAtual = new Comprador("samplep", 0, "samplep");
    Vendedor vendedorAtual = new Vendedor("samplevp", "samplevp");
    double valorCompra = 0;
    for (Comprador comprador : listaComprador) {
      if (comprador.getCpf().equals(cpfComprador)) {
        compradorAtual = comprador;
      }
    }

    for (Vendedor vendedor : listaVendedor) {
      if (vendedor.getCnpj().equals(cnpjVendedor)) {
        vendedorAtual = vendedor;
      }
    }

    for (String codAt : codigos) {
      for (Produto prod : vendedorAtual.getCatalogo()) {
        if (codAt.equals(prod.getCodigo())) {
          valorCompra += prod.getPreco();
        }
      }
    }

    if (compradorAtual.getSaldo() > valorCompra) {
      double novoSaldoComprador = compradorAtual.getSaldo() - valorCompra;
      double novoSaldoVendedor = vendedorAtual.getSaldo() + valorCompra;
      compradorAtual.setSaldo(novoSaldoComprador);
      vendedorAtual.setSaldo(novoSaldoVendedor);
      compradorAtual.addCompraRealizada();
      vendedorAtual.addVendaRealizada();
    }
  }

  public void debito(String cpfComprador, String cnpjVendedor, List<String> codigos) {
    Comprador compradorAtual = new Comprador("sampled", 0, "sampled");
    Vendedor vendedorAtual = new Vendedor("sampleva", "sampleva");
    double valorCompra = 0;
    for (Comprador comprador : listaComprador) {
      if (comprador.getCpf().equals(cpfComprador)) {
        compradorAtual = comprador;
      }
    }

    for (Vendedor vendedor : listaVendedor) {
      if (vendedor.getCnpj().equals(cnpjVendedor)) {
        vendedorAtual = vendedor;
      }
    }

    for (String codAt : codigos) {
      for (Produto prod : vendedorAtual.getCatalogo()) {
        if (codAt.equals(prod.getCodigo())) {
          valorCompra += prod.getPreco();
        }
      }
    }

    if (compradorAtual.getSaldo() > valorCompra) {
      double novoSaldoComprador = compradorAtual.getSaldo() - valorCompra;
      double novoSaldoVendedor = vendedorAtual.getSaldo() + valorCompra * (1 - taxaCartao);
      compradorAtual.setSaldo(novoSaldoComprador);
      vendedorAtual.setSaldo(novoSaldoVendedor);
      compradorAtual.addCompraRealizada();
      vendedorAtual.addVendaRealizada();
    }
  }

  public void credito(String cpfComprador, String cnpjVendedor, List<String> codigos) {
    Comprador compradorAtual = new Comprador("samplec", 0, "samplec");
    Vendedor vendedorAtual = new Vendedor("samplev", "samplev");
    double valorCompra = 0;
    for (Comprador comprador : listaComprador) {
      if (comprador.getCpf().equals(cpfComprador)) {
        compradorAtual = comprador;
      }
    }

    for (Vendedor vendedor : listaVendedor) {
      if (vendedor.getCnpj().equals(cnpjVendedor)) {
        vendedorAtual = vendedor;
      }
    }

    for (String codAt : codigos) {
      for (Produto prod : vendedorAtual.getCatalogo()) {
        if (codAt.equals(prod.getCodigo())) {
          valorCompra += prod.getPreco();
        }
      }
    }

    if (compradorAtual.getSaldo() > valorCompra) {
      double valorAPagar = valorCompra;
      double valorAReceber = valorCompra * (1 - taxaCartao);
      compradorAtual.addValorAPagar(valorAPagar);;
      vendedorAtual.addValorAReceber(valorAReceber);
      compradorAtual.addCompraRealizada();
      vendedorAtual.addVendaRealizada();
    }
  }

  public void boleto(String cpfComprador, String cnpjVendedor, List<String> codigos, LocalDate date) {
    Comprador compradorAtual = new Comprador("sample", 0, "sample");
    Vendedor vendedorAtual = new Vendedor("sampleve", "sampleve");
    double valorCompra = 0;
    for (Comprador comprador : listaComprador) {
      if (comprador.getCpf().equals(cpfComprador)) {
        compradorAtual = comprador;
      }
    }

    for (Vendedor vendedor : listaVendedor) {
      if (vendedor.getCnpj().equals(cnpjVendedor)) {
        vendedorAtual = vendedor;
      }
    }

    for (String codAt : codigos) {
      for (Produto prod : vendedorAtual.getCatalogo()) {
        if (codAt.equals(prod.getCodigo())) {
          valorCompra += prod.getPreco();
        }
      }
    }

    if (compradorAtual.getSaldo() > valorCompra && (date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now()))) {
      double novoSaldoComprador = compradorAtual.getSaldo() - valorCompra;
      double novoSaldoVendedor = vendedorAtual.getSaldo() + valorCompra - taxaBoleto;
      compradorAtual.setSaldo(novoSaldoComprador);
      vendedorAtual.setSaldo(novoSaldoVendedor);
      compradorAtual.addCompraRealizada();
      vendedorAtual.addVendaRealizada();
    }

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

  public void showProdutos() {
    for (Produto produto : listaProdutos) {
      System.out.println(produto);
    }
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
