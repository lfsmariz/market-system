package com.market.market_system.util.store.utils;

import com.market.market_system.util.user.Comprador;
import com.market.market_system.util.user.Vendedor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public abstract class FormaPagamento {

    public abstract void pagar(Set<Comprador> listaComprador, Set<Vendedor> listaVendedor, String cpfComprador, String cnpjVendedor, List<String> codigos, LocalDate helper);
}
