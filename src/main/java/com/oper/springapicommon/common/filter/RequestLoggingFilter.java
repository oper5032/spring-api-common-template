package com.oper.springapicommon.common.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
	
	protected void doFilterInternal(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        FilterChain filterChain)
	        throws ServletException, IOException {

	    long startTime = System.currentTimeMillis();
	    String traceId = UUID.randomUUID().toString().substring(0, 8);

	    MDC.put("traceId", traceId);

	    try {
	        String uri = request.getRequestURI();
	        String method = request.getMethod();
	        String ip = getClientIp(request);

	        log.info("[REQUEST] {} {} IP={}", method, uri, ip);

	        filterChain.doFilter(request, response);

	        long endTime = System.currentTimeMillis();

	        log.info(
	                "[RESPONSE] {} {} STATUS={} TIME={}ms",
	                method,
	                uri,
	                response.getStatus(),
	                (endTime - startTime)
	        );

	    } finally {
	        MDC.clear();
	    }
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
	    String uri = request.getRequestURI();

	    return uri.equals("/favicon.ico")
	            || uri.startsWith("/swagger-ui")
	            || uri.startsWith("/v3/api-docs")
	            || uri.startsWith("/actuator");
	}
	
	private String getClientIp(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");

	    if (ip == null || ip.isBlank()) {
	        ip = request.getRemoteAddr();
	    }

	    if ("0:0:0:0:0:0:0:1".equals(ip)) {
	        ip = "127.0.0.1";
	    }

	    return ip;
	}

}
