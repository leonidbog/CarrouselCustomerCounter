package boguslavskii.leonid.carrouselcustomercounter.controller;

import boguslavskii.leonid.carrouselcustomercounter.dto.Response;
import boguslavskii.leonid.carrouselcustomercounter.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final UserAuthService userAuthService;

    @PostMapping("registration")
    public Response registration(@RequestBody Response response){
        return userAuthService.signUp(response);
    }

    @PostMapping("login")
    public Response login(@RequestBody Response response){
        return userAuthService.login(response);
    }




}
