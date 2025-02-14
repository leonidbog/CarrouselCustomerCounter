package boguslavskii.leonid.carrouselcustomercounter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test/auth")
public class TestAuthenticatedController {

    @GetMapping("hello")
    public String helloWorld() {
        return "Hello authenticated LEONID and DMITRY!";
    }



}
