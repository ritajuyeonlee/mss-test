package com.musinsa.exception;

import com.musinsa.enumerable.ErrorCode;
import org.springframework.http.HttpStatus;

public class MerchandiseNotExistException extends ApplicationException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    private static final ErrorCode ERROR_CODE = ErrorCode.MERCHANDISE_NOT_EXIST;
    private static final String ERROR_MESSAGE = "존재하지 않는 회원입니다";

    public MerchandiseNotExistException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
