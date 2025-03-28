package boguslavskii.leonid.carrouselcustomercounter.dto;

import boguslavskii.leonid.carrouselcustomercounter.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String expirationTime;
    private String username;
    private String role;
    private String password;
    private User user;
    private boolean success;
    private Long recordId;

}
