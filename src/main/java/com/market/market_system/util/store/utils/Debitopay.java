package com.market.market_system.util.store.utils;

import com.market.market_system.util.user.Comprador;
import com.market.market_system.util.user.Vendedor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Debitopay extends FormaPagamento {

    private double taxaCartao = 0.05;

    @Override
    public void pagar(Set<Comprador> listaComprador, Set<Vendedor> listaVendedor, String cpfComprador, String cnpjVendedor, List<String> codigos, LocalDate helper) {
        Comprador compradorAtual = new Comprador();
        Vendedor vendedorAtual = new Vendedor();
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
            for (store.utils.Produto prod : vendedorAtual.getCatalogo()) {
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
}
