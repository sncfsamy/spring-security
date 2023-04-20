package com.wildcodeschool.myprojectwithsecurity.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorldController {
    @GetMapping("/")
    public String hello() {
        return "Hello Avenger !!!<br /><a href='/avengers/assemble'>Avengers assemble</a><br /><a href='/secret-bases'>Director page</a>";
    }

    @GetMapping("/avengers/assemble")
    public String avengers() {
        return "Avengers... Assemble";
    }

    @GetMapping("/secret-bases")
    public String director() {
        return "<a href='https://www.wildcodeschool.com/fr-FR'>Cliquez-ici</a>";
    }
}