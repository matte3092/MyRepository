package com.hermestrade.utility.library.controller.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hermestrade.utility.library.util.LogConstants;
import com.hermestrade.utility.library.util.ObjectFormatter;

/**
 * The Class ControllerInterceptorHandler.
 */
public class ControllerInterceptorHandler implements HandlerInterceptor {

	/** The Constant logger. */
	static final Logger logger = LoggerFactory.getLogger(ControllerInterceptorHandler.class);

	/** The controllers endpoint log enable. */
	private boolean controllersEndpointLogEnable;

	/**
	 * Instantiates a new controller interceptor handler.
	 *
	 * @param controllersEndpointLogEnable the controllers endpoint log enable
	 */
	public ControllerInterceptorHandler(boolean controllersEndpointLogEnable) {
		super();
		this.controllersEndpointLogEnable = controllersEndpointLogEnable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (this.controllersEndpointLogEnable) {
			logger.info(LogConstants.CONTROLLER_INFO_URL, "preHandle", request.getMethod(), request.getRequestURI());
			Map<String, String[]> parameters = request.getParameterMap();
			if (!parameters.isEmpty()) {
				String json = ObjectFormatter.asJsonString(parameters);
				logger.info(LogConstants.CONTROLLER_INFO_PARAMS, "", "", json);
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Nothing.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (controllersEndpointLogEnable) {
			logger.info(LogConstants.CONTROLLER_INFO_RESPONSE, "afterCompletion", request.getRequestURI(),
					response.getStatus());
		}
	}
}
