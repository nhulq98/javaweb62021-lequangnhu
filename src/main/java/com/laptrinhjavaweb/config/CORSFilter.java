package com.laptrinhjavaweb.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CORSFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

//	 function use to filter request
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*"); // allow any domain access the resource
	//	  response.setHeader("Access-Control-Allow-Origin", "https://facebook.com"); // specific domain
        response.setHeader("Access-Control-Allow-Credentials", "true"); // no need login!
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"); // specifies the method or methods allowed when accessing the resource.
		response.setHeader("Access-Control-Max-Age", "3600"); // indicates how long the results of a preflight request can be cached.
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");

		chain.doFilter(req, res); // allow request go over
	}

	@Override
	public void destroy() {
		
	}

}
