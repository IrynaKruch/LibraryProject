package ua.krucheniuk.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.constants.Path;

public class ExceptionFilter implements Filter {
	
    private static final Logger log=Logger.getLogger(ExceptionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch ( Throwable error) {
			log.error(error.getMessage());
			request.setAttribute("errMessage", error.getMessage());
			
			request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
		}

    		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
