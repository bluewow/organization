package com.daou.demo.organization.util.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessException extends RuntimeException {
    private ErrorCode code;

    public BusinessException(ErrorCode code) {
        this.code = code;
    }
}
