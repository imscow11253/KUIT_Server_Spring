package kuit2.server.ServerApplication.controller;

import kuit2.server.ServerApplication.dio.LoginRequest;
import kuit2.server.ServerApplication.dio.PostUserRequest;
import kuit2.server.ServerApplication.dio.PostUserResponse;
import kuit2.server.ServerApplication.exception.UserException;
import kuit2.server.ServerApplication.response.BaseResponse;
import kuit2.server.ServerApplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kuit2.server.ServerApplication.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static kuit2.server.ServerApplication.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public BaseResponse<PostUserResponse> createUser(@Validated @RequestBody PostUserRequest postUserRequest, BindingResult bindingResult){

        log.info("userController.createUser");

        if(bindingResult.hasErrors()){
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }

        return new BaseResponse<>(this.userService.createUserService(postUserRequest));
    }

    @PostMapping("/login")
    public BaseResponse<PostUserResponse> userLogin(@Validated @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        log.info("userController.userLogin");

        if(bindingResult.hasErrors()){
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }

        return new BaseResponse<>(this.userService.loginService(loginRequest));
    }

}
