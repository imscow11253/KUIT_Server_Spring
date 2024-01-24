package kuit2.server.ServerApplication.dio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
@Getter
public class PostUserRequest {

    @Length(max=20)
    private String login_id;

    @Length(max=200)
    private String login_pw ;

    @Length(max=10)
    private String name;

    @Email
    @Length(max=50)
    private String email;


    @Length(max =13)
    private String phone_number;
}
