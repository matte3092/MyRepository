package com.hermestrade.utility.library.controller.util;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hermestrade.utility.library.util.LogConstants;
import com.hermestrade.utility.library.util.ObjectFormatter;

/**
 * The Class CustomRequestBodyAdviceAdapter.
 */
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

	/** The controllers endpoint log enable. */
	@Value("${summer.controllers.endpoint.log:true}")
	private boolean controllersEndpointLogEnable;

	/** The controllers body log enable. */
	@Value("${summer.controllers.body.log:false}")
	private boolean controllersBodyLogEnable;

	/** The http servlet request. */
	@Autowired
	HttpServletRequest httpServletRequest;

	/** The Constant logger. */
	static final Logger logger = LoggerFactory.getLogger(CustomRequestBodyAdviceAdapter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice#
	 * supports(org.springframework.core.MethodParameter, java.lang.reflect.Type,
	 * java.lang.Class)
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.method.annotation.
	 * RequestBodyAdviceAdapter#afterBodyRead(java.lang.Object,
	 * org.springframework.http.HttpInputMessage,
	 * org.springframework.core.MethodParameter, java.lang.reflect.Type,
	 * java.lang.Class)
	 */
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		try {
			if (controllersEndpointLogEnable && controllersBodyLogEnable) {
				String json = ObjectFormatter.asJsonString(body);
				logger.info(LogConstants.CONTROLLER_INFO_BODY, "", "", json);
			}
		} catch (JsonProcessingException e) {
			logger.error("[afterBodyRead]: error while parsing body: ", e);
		}
		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
	}
}
