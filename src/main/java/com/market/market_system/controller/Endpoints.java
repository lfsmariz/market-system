package com.market.market_system.controller;

import com.market.market_system.model.Bodypayment;
import com.market.market_system.util.store.Store;
import com.market.market_system.util.store.utils.Produto;
import com.market.market_system.util.user.Comprador;
import com.market.market_system.util.user.Vendedor;
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
        Comprador tmpComp1 = new Comprador("Comprador1", 100, "11111");
        Comprador tmpComp2 = new Comprador("Comprador2", 200, "22222");
        Comprador tmpComp3= new Comprador("Comprador3", 300, "33333");

        Vendedor tmpVend1 = new Vendedor("Vendedor4", "4444");
        Vendedor tmpVend2 = new Vendedor("Vendedor5", "5555");
        Vendedor tmpVend3 = new Vendedor("Vendedor6", "6666");

        Produto tmpProd1 = new Produto("produto1", "PRODUTO1", 10.0);
        Produto tmpProd2 = new Produto("produto2", "PRODUTO2", 20.0);
        Produto tmpProd3 = new Produto("produto3", "PRODUTO3", 30.0);
        Produto tmpProd4 = new Produto("produto4", "PRODUTO4", 40.0);
        Produto tmpProd5 = new Produto("produto5", "PRODUTO5", 50.0);

        tmpVend1.addCatalog(tmpProd1);
        tmpVend1.addCatalog(tmpProd2);
        tmpVend1.addCatalog(tmpProd3);

        tmpVend2.addCatalog(tmpProd4);
        tmpVend2.addCatalog(tmpProd5);

        tmpVend3.addCatalog(tmpProd1);
        tmpVend3.addCatalog(tmpProd2);
        tmpVend3.addCatalog(tmpProd3);
        tmpVend3.addCatalog(tmpProd4);
        tmpVend3.addCatalog(tmpProd5);

        this.store.addComprador(tmpComp1);
        this.store.addComprador(tmpComp2);
        this.store.addComprador(tmpComp3);
        this.store.addVendedor(tmpVend1);
        this.store.addVendedor(tmpVend2);
        this.store.addVendedor(tmpVend3);

        this.store.addProduto(tmpProd1);
        this.store.addProduto(tmpProd2);
        this.store.addProduto(tmpProd3);
        this.store.addProduto(tmpProd4);
        this.store.addProduto(tmpProd5);
    }

    @GetMapping("/comprador")
    public Map compradorByID(@RequestParam String id) {
        Map tmpComprador = store.showComprador(id);
        return tmpComprador;
    }

    @GetMapping("/compradores")
    public Map allCompradores() {
        Map tmpCompradores = store.showCompradores();
        return tmpCompradores;
    }

    @GetMapping("/vendedores")
    public Map allVendedores() {
        Map tmpVendedores = store.showVendedores();
        return tmpVendedores;
    }

    @GetMapping("/vendedor")
    public Map vendedorByID(@RequestParam String id) {
        Map tmpVendedor = store.showVendedor(id);
        return tmpVendedor;
    }

    @PostMapping("/pix")
    public Map pagarPix(@RequestBody Bodypayment body){
        Map<String, String> tmpMap = new HashMap<>();
        this.store.pix(body.getCpfComprador(), body.getCnpjVendedor(), body.getCodigosProdutos());
        return tmpMap;
    }

    @PostMapping("/boleto")
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

    @PostMapping("/debito")
    public Map pagarDebito(@RequestBody Bodypayment body){
        Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("message", body.getCpfComprador());

        this.store.debito(body.getCpfComprador(), body.getCnpjVendedor(), body.getCodigosProdutos());
        return tmpMap;
    }

    @PostMapping("/credito")
    public Map pagarCredito(@RequestBody Bodypayment body){
        Map<String, String> tmpMap = new HashMap<>();
        tmpMap.put("message", body.getCpfComprador());

        this.store.credito(body.getCpfComprador(), body.getCnpjVendedor(), body.getCodigosProdutos());
        return tmpMap;
    }

    @GetMapping("/produtos")
    public Map<String ,List<Map<String, String>>> produtos(){
        return this.store.showProdutos();
    }

    @GetMapping("/produtos-vendedor")
    public Map produtos(@RequestParam String id) {
        Map tmpCatalogo = store.showVendedorProd(id);
        return tmpCatalogo;
    }
}
