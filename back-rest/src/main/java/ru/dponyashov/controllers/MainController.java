package ru.dponyashov.controllers;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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