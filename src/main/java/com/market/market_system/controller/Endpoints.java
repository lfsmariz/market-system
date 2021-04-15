package com.market.market_system.controller;

import com.market.market_system.model.Bodypayment;
import com.market.market_system.util.store.Store;
import com.market.market_system.util.user.Comprador;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class Endpoints {

    private Store store;
    private final Bodypayment bodypayment;

    public Endpoints(Store store, Bodypayment bodypayment) {
        this.store = store;
        this.bodypayment = bodypayment;
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

    @GetMapping("/pix")
    public Map pagarPix(@RequestBody Bodypayment body){
        Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("message", "deu bom");
        this.store.pix(body.getCpfComprador(), body.getCnpjVendedor(), body.getCodigosProdutos());
        return tmpMap;
    }

    @GetMapping("/boleto")
    public Map pagarBoleto(@RequestBody Bodypayment body){
        Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("message", body.getCpfComprador());

        List<String> tmpdate = Arrays.asList(body.getDateBoleto().split("/"));

        Integer day = Integer.parseInt(tmpdate.get(0));
        Integer mes = Integer.parseInt(tmpdate.get(1));
        Integer ano = Integer.parseInt(tmpdate.get(2));

        LocalDate convertToData = LocalDate.of(ano, mes, day);

        this.store.boleto(body.getCpfComprador(), body.getCnpjVendedor(), body.getCodigosProdutos(), convertToData);

        return tmpMap;
    }
}
