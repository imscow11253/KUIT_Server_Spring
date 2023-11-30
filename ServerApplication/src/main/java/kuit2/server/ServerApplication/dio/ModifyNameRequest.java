package kuit2.server.ServerApplication.dio;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ModifyNameRequest {

    @Positive
    @Min(1)
    long user_id;

    @NotBlank
    @Length(max = 10)
    String requestName;
}
