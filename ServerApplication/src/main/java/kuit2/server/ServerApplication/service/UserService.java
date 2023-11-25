package kuit2.server.ServerApplication.service;

import kuit2.server.ServerApplication.dao.UserDao;
import kuit2.server.ServerApplication.dio.PostUserRequest;
import kuit2.server.ServerApplication.dio.PostUserResponse;
import kuit2.server.ServerApplication.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static kuit2.server.ServerApplication.response.status.BaseExceptionResponseStatus.DUPLICATE_EMAIL;
import static kuit2.server.ServerApplication.response.status.BaseExceptionResponseStatus.DUPLICATE_NICKNAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    public PostUserResponse createUserService(PostUserRequest postUserRequest){
        log.info("UserService.createUserService");

        this.validateName(postUserRequest.getName());
        this.validateEmail(postUserRequest.getEmail());

        long userId = userDao.createUser(postUserRequest);

        return new PostUserResponse(userId);
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
