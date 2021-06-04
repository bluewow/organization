package com.daou.demo.organization.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    ERROR_CODE_001("BAD_REQUEST", "부서코드와 일치하는 부서를 찾을 수 없습니다", HttpStatus.BAD_REQUEST),
    ERROR_CODE_002("INTERMAL_SERVER_ERROR", "내부적인 오류가 발생하였습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_CODE_003("BAD_REQUEST", "검색대상을 선택해 주세요", HttpStatus.BAD_REQUEST),
    ERROR_CODE_004("BAD_REQUEST", "부서코드가 입력되었습니다", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
