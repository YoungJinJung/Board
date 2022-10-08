package com.example.board.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
    @GetMapping("/temp/home")
    public String tempHome(){
        return "/home.html";
    }
    @GetMapping("/temp/jsp")
    public String tempTest(){
        return "test";
    }
}
