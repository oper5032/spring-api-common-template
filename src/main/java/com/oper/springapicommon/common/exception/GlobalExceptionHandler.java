package com.oper.springapicommon.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.oper.springapicommon.common.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> businessException(BusinessException e) {
		
		ErrorCode errorCode = e.getErrorCode();
		
		return ResponseEntity
				.status(errorCode.getStatus())
				.body(
					ApiResponse.fail(
							errorCode.getStatus(),
							errorCode.getCode(),
							errorCode.getMessage()
							)
					);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> exception(Exception e) {
		return ResponseEntity
				.status(500)
				.body(
						ApiResponse.fail(
								500,
								"INTERNAL_SERVER_ERROR",
								e.getMessage()
								)
						);
	}
}
