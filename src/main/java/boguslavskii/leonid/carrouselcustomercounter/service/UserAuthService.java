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
        Response newRequestResponse = new Response();
        if (userRepository.findByUsername(requestResponse.getUsername()).isPresent()) {
            newRequestResponse.setError("Username already exists");
            newRequestResponse.setStatusCode(400);
            return newRequestResponse;
        }

        try {
            User user = new User();
            user.setUsername(requestResponse.getUsername());
            user.setPassword(passwordEncoder.encode(requestResponse.getPassword()));
            user.setRoles(requestResponse.getRole());
            User savedAppUser = userRepository.save(user);
            if (savedAppUser != null && savedAppUser.getId() > 0) {
                newRequestResponse.setUser(savedAppUser);
                newRequestResponse.setMessage("Success");
                newRequestResponse.setStatusCode(200);
            }
        } catch (Exception e) {
            newRequestResponse.setStatusCode(500);
            newRequestResponse.setMessage(e.getMessage());
        }
        return newRequestResponse;
    }

    public Response login(Response signInRequest) {
        Response response = new Response();
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(signInRequest
                            .getUsername(), signInRequest.getPassword()));
            var user = userRepository.findByUsername(signInRequest.getUsername());
            var jwt = jwtUtil.generateToken(new MyUserDetails(user.get()));
            response.setStatusCode(200);
            response.setMessage("Success");
            response.setToken(jwt);
            response.setExpirationTime("24");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }


}
