package com.oper.springapicommon.health;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oper.springapicommon.common.exception.BusinessException;
import com.oper.springapicommon.common.exception.ErrorCode;
import com.oper.springapicommon.common.response.ApiResponse;

@RestController
public class HealthController {
	
	@GetMapping("/api/health")
    public ApiResponse<Map<String, Object>> health() {
        return ApiResponse.success(Map.of(
                "status", "UP",
                "service", "spring-api-common"
        ));
    }
	
	@GetMapping("/api/error")
	public ApiResponse<Void> error() {

	    throw new BusinessException(
	            ErrorCode.NOT_FOUND
	    );
	}
}
