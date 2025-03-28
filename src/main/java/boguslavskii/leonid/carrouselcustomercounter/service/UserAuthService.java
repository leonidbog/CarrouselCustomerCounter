package boguslavskii.leonid.carrouselcustomercounter.service;

import boguslavskii.leonid.carrouselcustomercounter.dto.MyUserDetails;
import boguslavskii.leonid.carrouselcustomercounter.dto.Response;
import boguslavskii.leonid.carrouselcustomercounter.entities.User;
import boguslavskii.leonid.carrouselcustomercounter.repository.UserRepository;
import boguslavskii.leonid.carrouselcustomercounter.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public Response signUp(Response requestResponse) {
        if (userRepository.findByUsername(requestResponse.getUsername()).isPresent()) {
            return Response.builder()
                    .error("Username already exists")
                    .statusCode(400)
                    .build();
        }
        Response.ResponseBuilder responseBuilder = Response.builder();

        try {
            User user = new User();
            user.setUsername(requestResponse.getUsername());
            user.setPassword(passwordEncoder.encode(requestResponse.getPassword()));
            user.setRoles(requestResponse.getRole());
            User savedAppUser = userRepository.save(user);
            if (savedAppUser != null && savedAppUser.getId() > 0) {
                responseBuilder.user(savedAppUser);
                responseBuilder.message("Success");
                responseBuilder.statusCode(200);
            }
        } catch (Exception e) {
            responseBuilder.statusCode(500);
            responseBuilder.message(e.getMessage());
        }
        return responseBuilder.build();
    }

    public Response login(Response signInRequest) {
        Response.ResponseBuilder response = Response.builder();
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(signInRequest
                            .getUsername(), signInRequest.getPassword()));
            var user = userRepository.findByUsername(signInRequest.getUsername());
            var jwt = jwtUtil.generateToken(new MyUserDetails(user.get()));
            response.statusCode(200);
            response.message("Success");
            response.role(user.get().getRoles());
            response.token(jwt);
            response.expirationTime("24");

        }catch (Exception e){
            response.statusCode(500);
            response.error(e.getMessage());
        }
        return response.build();
    }




}
