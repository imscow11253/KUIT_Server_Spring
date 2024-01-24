package kuit2.server.ServerApplication.dio;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
@Getter
public class LoginRequest {

    @NotBlank
    @Length(max=20)
    String login_id;

    @NotBlank
    @Length(max =200)
    String login_pw;
}
