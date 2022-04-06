package kr.co.iei.map.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.map.controller.SimpleCORSFilter.Component;

@Component
public class SimpleCORSFilter implements Filter {

	public @interface Component {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response= (HttpServletResponse) res;
		
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		chain.doFilter(req, res);
	}
	
	@Override
	public void init(FilterConfig filterConfig) {}
	
	@Override
	public void destroy() {}
}
