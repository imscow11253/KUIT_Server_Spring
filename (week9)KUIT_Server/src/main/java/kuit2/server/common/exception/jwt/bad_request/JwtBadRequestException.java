package kuit2.server.common.exception.jwt.bad_request;

import kuit2.server.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtBadRequestException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JwtBadRequestException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
