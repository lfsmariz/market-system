package com.market.market_system.util.store.utils;

import com.market.market_system.util.user.Comprador;
import com.market.market_system.util.user.Vendedor;
import com.market.market_system.util.store.utils.Produto;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Boletopay extends FormaPagamento{

    private double taxaBoleto = 10.0;

    public void pagar(Set<Comprador> listaComprador, Set<Vendedor> listaVendedor, String cpfComprador, String cnpjVendedor, List<String> codigos, LocalDate date) {
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
}
