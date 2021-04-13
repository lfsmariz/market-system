package com.market.market_system.controller;

import com.market.market_system.src.store.Store;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class Endpoints {

    private Store loja = new Store();

    @GetMapping("/hello")
    public String hello_world() {
        return "agora foi";
    }
}
