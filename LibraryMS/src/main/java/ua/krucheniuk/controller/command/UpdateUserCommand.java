package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.AuthService;

public class UpdateUserCommand implements Command{
	
	AuthService authService = AuthService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("sessionUser");
	 	String fullname = request.getParameter("fullname");
	 	if(fullname != "")
	 		user.setFullname(fullname);
	 	String email = request.getParameter("email");
	 	if(email != "")
	 		user.setEmail(email);
	 	String login = request.getParameter("login");
	 	if(login != "")
	 		user.setLogin(login);
	 	String password = request.getParameter("password");
	 	if(password != "")
	 		user.setPassword(password);
	 		    
	    String userRegistered = authService.update(user);
	    
	    if(userRegistered.equals("SUCCESS")) {  
	    request.getSession().setAttribute("message", "The information is succesfully updated.");
	    request.getSession().setAttribute("sessionUser", user);
	    }
	    else   
	    {  request.setAttribute("errMessage", userRegistered);}
	       return Path.PERSONAL_PAGE;   
}

}
