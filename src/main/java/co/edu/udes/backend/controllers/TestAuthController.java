package co.edu.udes.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAuthController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";

    }

    @RequestMapping("/hello-secured")
    public String helloSecured(){
        return "Hello World Secured";

    }
    @RequestMapping("/hello-secured2")
    public String helloSecured2(){
        return "Hello World Secured2";

    }
}
