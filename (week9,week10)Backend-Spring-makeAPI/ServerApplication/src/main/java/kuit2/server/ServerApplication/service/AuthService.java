package kuit2.server.ServerApplication.service;

import kuit2.server.ServerApplication.dao.UserDao;
import kuit2.server.ServerApplication.dio.ModifyNameRequest;
import kuit2.server.ServerApplication.dio.ModifyNameResponse;
import kuit2.server.ServerApplication.exception.jwt.unauthorized.JwtUnauthorizedTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import static kuit2.server.ServerApplication.response.status.BaseExceptionResponseStatus.TOKEN_MISMATCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userDao;

    public ModifyNameResponse modifyUserNameService(ModifyNameRequest modifyNameRequest) {
        log.info("AuthService.modifyUserNameService");

        long user_id = this.userDao.updateUserNameField(modifyNameRequest.getUser_id(), modifyNameRequest.getRequestName());
        String responseName = modifyNameRequest.getRequestName();
        return new ModifyNameResponse(user_id, responseName);
    }

    public long getUserIdByEmail(String email) {
        try {
            return userDao.getUserIdByEmail(email);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new JwtUnauthorizedTokenException(TOKEN_MISMATCH);
        }
    }
}
