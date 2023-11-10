package kuit2.server.common.exception.jwt;

import kuit2.server.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtNoTokenException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JwtNoTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
