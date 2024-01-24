package kuit2.server.ServerApplication.service;

import kuit2.server.ServerApplication.dao.UserDao;
import kuit2.server.ServerApplication.dio.LoginRequest;
import kuit2.server.ServerApplication.dio.PostUserRequest;
import kuit2.server.ServerApplication.dio.PostUserResponse;
import kuit2.server.ServerApplication.exception.UserException;
import kuit2.server.ServerApplication.util.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import static kuit2.server.ServerApplication.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final JwtProvider jwtProvider;

    public PostUserResponse createUserService(PostUserRequest postUserRequest){
        log.info("UserService.createUserService");

        this.validateName(postUserRequest.getName());
        this.validateEmail(postUserRequest.getEmail());

        long userId = userDao.createUser(postUserRequest);
        String jwt = jwtProvider.createToken(postUserRequest.getEmail(),userId);

        return new PostUserResponse(userId,jwt);
    }

    public PostUserResponse loginService(LoginRequest loginRequest) {
        log.info("UserService.loginService");

        this.checkLoginInfo(loginRequest);

        long userId = this.userDao.getUseIdByLoginId(loginRequest.getLogin_id());

        String newJwt = jwtProvider.createToken(this.userDao.getEmailByLoginId(loginRequest.getLogin_id()),userId);

        return new PostUserResponse(userId, newJwt);
    }

    private void checkLoginInfo(LoginRequest loginRequest) {
        String login_id = loginRequest.getLogin_id();
        String login_pw = loginRequest.getLogin_pw();

        if(!this.userDao.isThereLoginId(login_id)){
            throw new UserException(USER_NOT_FOUND);
        }
        if(!this.userDao.matchPW(login_id, login_pw)){
            throw new UserException(PASSWORD_NO_MATCH);
        }
     }

    private void validateEmail(String email) {
        if (userDao.hasDuplicateEmail(email)) {
            throw new UserException(DUPLICATE_EMAIL);
        }
    }

    private void validateName(String nickname) {
        if (userDao.hasDuplicateName(nickname)) {
            throw new UserException(DUPLICATE_NICKNAME);
        }
    }


}
