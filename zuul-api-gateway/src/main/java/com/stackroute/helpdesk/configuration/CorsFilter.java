//package com.stackroute.helpdesk.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.logging.LogRecord;
//
//@Configuration
//public class CorsFilter implements Filter {
//
//	public CorsFilter(UrlBasedCorsConfigurationSource source) {
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//
//		final HttpServletResponse response = (HttpServletResponse) res;
//		      /*  System.out.println("CORS FILTER WORKING IN GATEWAY:::");
//
//
//		        response.setHeader("Access-Control-Allow-Origin", "*");
//		        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE,OPTIONS");
//		        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type,x-requested-with");
//		        response.setHeader("Access-Control-Max-Age", "3600");*/
//		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
//			response.setStatus(HttpServletResponse.SC_OK);
//			return;
//		} else {
//			chain.doFilter(req, res);
//		}
//	}
//
//}
