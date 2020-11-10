package com.aguaja.api.controller;

import com.aguaja.api.domain.Teste;
import com.aguaja.api.repositories.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    TesteRepository tr;

    @GetMapping("")
    public Teste welcome() {
        Teste usu;
        usu = new Teste( 1L, "Elvis", 30);
        tr.save(usu);

        return usu;
    }
}
