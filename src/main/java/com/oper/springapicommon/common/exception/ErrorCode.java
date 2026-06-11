package com.oper.springapicommon.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	INVALID_PARAMETER(400, "INVALID_PARAMETER", "잘못된 요청입니다."),
	NOT_FOUND(404, "NOT_FOUND", "데이터가 존재하지 않습니다."),
    DUPLICATED(409, "DUPLICATED", "이미 존재하는 데이터입니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.");
	
	private final int status;
    private final String code;
    private final String message;
}
