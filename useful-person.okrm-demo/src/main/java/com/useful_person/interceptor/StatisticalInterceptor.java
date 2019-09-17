package com.useful_person.interceptor;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StatisticalInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", new Date().getTime());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long start = (long) request.getAttribute("startTime");
		System.out.println("post interceptor handle 耗时：" + (new Date().getTime() - start) + "ms");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String remoteUser = request.getRemoteUser();
		String remoteAddress = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		System.out.println("remoteUser: " + remoteUser + ", remoteAddress: " + remoteAddress + ", remoteHost: "
				+ remoteHost + ", remotePort: " + remotePort);

//		Enumeration<String> headerNames = request.getHeaderNames();
//		while (headerNames.hasMoreElements()) {
//			String headerName = headerNames.nextElement();
//			String header = request.getHeader(headerName);
//			System.out.println(headerName + ": " + header);
//		}
		// headers中可以获取host、accept、upgrade-insecure-requests、cookie、user-agent、accept-language、accept-encoding、connection等
		long start = (long) request.getAttribute("startTime");
		System.out.println("after interceptor completion 耗时：" + (new Date().getTime() - start) + "ms");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
