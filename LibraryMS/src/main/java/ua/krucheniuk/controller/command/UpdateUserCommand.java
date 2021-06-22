package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.AuthService;

public class UpdateUserCommand implements Command{
	
    private static final Logger log=Logger.getLogger(AuthService.class);

	AuthService authService = AuthService.getInstance();
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("sessionUser");
	 	String fullname = request.getParameter("fullname");
        if(authService.checkName(fullname)) 
	 		user.setFullname(fullname);
	 	String email = request.getParameter("email");
        if(authService.checkEmail(email)) 
	 		user.setEmail(email);
	 	String login = request.getParameter("login");
        if(authService.checkLogin(login)) 
	 		user.setLogin(login);
	 	String password = request.getParameter("password");
        if(authService.checkPassword(password)) 
	 		user.setPassword(password);
	 		    
	    String userRegistered = authService.update(user);
	    
	    String locale = (String) request.getSession().getAttribute("locale");
	    if(userRegistered.equals("SUCCESS")) {  
	    request.getSession().setAttribute("message", Messages.getInstance(locale).getString(Messages.UPDATE_USER));
	    request.getSession().setAttribute("sessionUser", user);
	    }
	    else   
	    {  request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.CANT_UPDATE_USER));}
	       return Path.PERSONAL_PAGE;   
}

}
