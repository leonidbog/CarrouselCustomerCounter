package boguslavskii.leonid.carrouselcustomercounter.controller;

import boguslavskii.leonid.carrouselcustomercounter.dto.Response;
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

    @GetMapping("login")
    public Response login() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setMessage("Success");
        return response;
    }



}
