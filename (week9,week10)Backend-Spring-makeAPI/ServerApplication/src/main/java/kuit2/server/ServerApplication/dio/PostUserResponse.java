package kuit2.server.ServerApplication.dio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserResponse {
    private long login_Id;
    private String jwt;
}
