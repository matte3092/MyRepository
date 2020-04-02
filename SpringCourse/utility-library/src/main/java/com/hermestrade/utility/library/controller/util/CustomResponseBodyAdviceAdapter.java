package com.hermestrade.utility.library.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hermestrade.utility.library.util.LogConstants;
import com.hermestrade.utility.library.util.ObjectFormatter;

/**
 * The Class CustomResponseBodyAdviceAdapter.
 */
@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

	/** The controllers endpoint log enable. */
	@Value("${summer.controllers.endpoint.log:true}")
	private boolean controllersEndpointLogEnable;

	/** The controllers body log enable. */
	@Value("${summer.controllers.body.log:false}")
	private boolean controllersBodyLogEnable;

	/** The Constant logger. */
	static final Logger logger = LoggerFactory.getLogger(CustomResponseBodyAdviceAdapter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#
	 * supports(org.springframework.core.MethodParameter, java.lang.Class)
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#
	 * beforeBodyWrite(java.lang.Object, org.springframework.core.MethodParameter,
	 * org.springframework.http.MediaType, java.lang.Class,
	 * org.springframework.http.server.ServerHttpRequest,
	 * org.springframework.http.server.ServerHttpResponse)
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		if (request instanceof ServletServerHttpRequest && response instanceof ServletServerHttpResponse) {
			try {
				if (controllersEndpointLogEnable && controllersBodyLogEnable) {
					String json = ObjectFormatter.asJsonString(body);
					logger.info(LogConstants.CONTROLLER_INFO_RETURNING_BODY, "beforeBodyWrite", json);
				}
			} catch (JsonProcessingException e) {
				logger.error(LogConstants.BEFORE_BODY_WRITE_MESSAGE, e);
			}
		}
		return body;
	}

}
