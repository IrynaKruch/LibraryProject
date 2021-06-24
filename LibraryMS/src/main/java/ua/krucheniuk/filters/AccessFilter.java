package ua.krucheniuk.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;

public class AccessFilter implements Filter {
	
    private List<String> commonCommands = new ArrayList<>();
    private List<String> readerCommands = new ArrayList<>();
    private List<String> librarianCommands = new ArrayList<>();



	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		commonCommands.add("catalogue");
		commonCommands.add("register");
		commonCommands.add("login");
		commonCommands.add("searchBook");
		commonCommands.add("sortBooks");
        
		readerCommands.add("order");
		readerCommands.add("readerInfo");
		readerCommands.add("updateUser");
		readerCommands.add("logout");

		
		librarianCommands.add("showOrders");
		librarianCommands.add("processOrder");
		librarianCommands.add("returnBook");
		librarianCommands.add("readers");
		librarianCommands.add("readerforAdmin");
		librarianCommands.add("searchOrders");
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String locale = (String) req.getSession().getAttribute("locale");
		if (accessAllowed(request)) {
			chain.doFilter(request, response);
		} else {
			String errorMessage = Messages.getInstance(locale).getString(Messages.ACCESS_DENIED);

//					 "You do not have permission to access the requested resource, please contact administrator";
			
			request.setAttribute("errMessage", errorMessage);
			
			request.getRequestDispatcher(Path.ERROR_PAGE)
					.forward(request, response);
		}
        
			
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	private boolean accessAllowed (ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String command = httpRequest.getRequestURI();
		command = command.replaceAll(".*/LibraryMS/" , "");
	if (command == null || command.isEmpty())
		return false;
	
	if (commonCommands.contains(command))
		return true;
	
	HttpSession session = httpRequest.getSession(false);
	if (session == null) 
		return false;
	
	Optional <User> user = Optional.ofNullable((User)session.getAttribute("sessionUser"));
	if (user.isPresent()){
	String userRole = user.get().getRole();
	
	if (userRole == "ADMIN")
		return true;
	
	if (userRole == "READER") {
		return readerCommands.contains(command) || commonCommands.contains(command);}

	if (userRole == "LIBRARIAN")
		{return librarianCommands.contains(command) || readerCommands.contains(command) || commonCommands.contains(command);}
	}
	return false;
	}

	
}
