package com.market.market_system.controller;

import com.market.market_system.util.store.Store;
import com.market.market_system.util.user.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class Endpoints {

    private Store store;

    public Endpoints(Store store) {
        this.store = store;
        Comprador tmpComp1 = new Comprador("Comprador", 120, "11111");
        store.addComprador(tmpComp1);
    }

    @GetMapping("/comprador")
    public Map compradorByID(@RequestParam String id) {
        Map tmpComprador = store.showComprador(id);
        return tmpComprador;
    }

    @GetMapping("/vendedor")
    public Map vendedorByID(@RequestParam String id) {
        Map tmpVendedor = store.showVendedor(id);
        return tmpVendedor;
    }
}
