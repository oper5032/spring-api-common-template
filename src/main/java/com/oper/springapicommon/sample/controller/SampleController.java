package com.oper.springapicommon.sample.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oper.springapicommon.common.response.ApiResponse;
import com.oper.springapicommon.sample.dto.SampleRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sample")
public class SampleController {
	
	@PostMapping("/api/sample")
	public ApiResponse<?> save(@Valid @RequestBody SampleRequest request) {
		
		return ApiResponse.success(request);
	}

}
