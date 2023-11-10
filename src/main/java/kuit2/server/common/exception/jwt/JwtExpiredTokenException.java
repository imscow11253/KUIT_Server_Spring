package kuit2.server.common.exception.jwt;

import kuit2.server.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtExpiredTokenException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JwtExpiredTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
