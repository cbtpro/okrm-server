package com.useful.person.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author peter
 *
 */
@Log4j2
@Component
public class StatisticalInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long start = (long) request.getAttribute("startTime");
		log.info("post interceptor handle 耗时：" + (System.currentTimeMillis() - start) + "ms");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String remoteUser = request.getRemoteUser();
		String remoteAddress = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		log.info("remoteUser: " + remoteUser + ", remoteAddress: " + remoteAddress + ", remoteHost: "
				+ remoteHost + ", remotePort: " + remotePort);

		long start = (long) request.getAttribute("startTime");
		log.info("after interceptor completion 耗时：" + (System.currentTimeMillis() - start) + "ms");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
