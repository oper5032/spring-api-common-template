package com.oper.springapicommon.sample.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SampleRequest {

	@NotBlank(message = "userId는 필수입니다.")
	private String userId;
	
	@NotBlank(message = "userName은 필수입니다.")
	private String userName;
}
