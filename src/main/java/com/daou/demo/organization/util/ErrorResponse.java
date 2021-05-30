package com.daou.demo.organization.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String code;
    private String message;

    ErrorResponse(ErrorCode errorCode) {
        code = errorCode.getCode();
        message = errorCode.getMessage();
    }
}
