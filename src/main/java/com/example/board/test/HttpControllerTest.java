package com.example.board.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {
    @GetMapping("/http/get")
    public String lombokTest() {
        Member m = new Member(1, "ssar", "1234", "e-mail","","");
        System.out.println("Getter" + m.getId());
        m.setId(511);
        System.out.println("Setter" + m.getId());
        return "lombok Test end";
    }

    public String getTest(@RequestParam Member m) {
        return "Request Get" + m.getId();
    }

    /**
     * Data가 text/plain이면 Mapping할 수 없음.
     * But Applicatio/json이면 MessageConverter가 객체에 Mapping해줌
     */
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return "Request Post" + m.getId();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "Request Put";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(@RequestBody Member m) {
        return "Request Delete";
    }

}
