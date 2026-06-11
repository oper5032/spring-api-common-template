package com.oper.springapicommon.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
	
	private int status;
	private String code;
	private String message;
	private T data;
	
	public static <T> ApiResponse<T> success(T data) {
		return ApiResponse.<T>builder()
				.status(200)
				.code("SUCCESS")
				.message("요청이 정상 처리 되었습니다.")
				.data(data)
				.build();
	}
	
	private static ApiResponse<Void> success() {
		return ApiResponse.<Void>builder()
				.status(200)
				.code("SUCCESS")
				.message("요청이 정상 처리 되었습니다.")
				.build();
	}
	
	public static ApiResponse<Void> fail(
	        int status,
	        String code,
	        String message) {

	    return ApiResponse.<Void>builder()
	            .status(status)
	            .code(code)
	            .message(message)
	            .build();
	}
}
