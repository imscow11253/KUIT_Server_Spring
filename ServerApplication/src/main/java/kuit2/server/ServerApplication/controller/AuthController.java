package kuit2.server.ServerApplication.controller;

import kuit2.server.ServerApplication.dio.ModifyNameRequest;
import kuit2.server.ServerApplication.dio.ModifyNameResponse;
import kuit2.server.ServerApplication.exception.UserException;
import kuit2.server.ServerApplication.response.BaseResponse;
import kuit2.server.ServerApplication.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static kuit2.server.ServerApplication.response.status.BaseExceptionResponseStatus.BAD_REQUEST;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PatchMapping("/name")
    public BaseResponse<ModifyNameResponse> modifyUserName(@Validated @RequestBody ModifyNameRequest modifyNameRequest, BindingResult bindingResult){
        log.info("AuthController.modifyUserName");

        if(bindingResult.hasErrors()){
            throw new UserException(BAD_REQUEST);
        }

        return new BaseResponse<>(this.authService.modifyUserNameService(modifyNameRequest));
    }
}
