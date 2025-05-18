package ru.dponyashov.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
//@RequestMapping
public class MainController {

    @GetMapping({"/", "hello", "index", "main"})
    public String helloPage(){
        return "Hi, all!";
    }


}