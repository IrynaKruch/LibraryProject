package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.AuthService;

public class RegisterCommand implements Command{
    
	AuthService authService = AuthService.getInstance();
	
	public String execute (HttpServletRequest request, HttpServletResponse response) {
        String locale=(String) request.getSession().getAttribute("locale");
        String name = request.getParameter("fullname");
        String email =  request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        boolean error=false;

		 	User user = new User();
	        if(!authService.checkName(name)){
	            request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.NAME_ERROR));
	            error=true;
	        } else { 
	        	user.setFullname(name);}
	        
	        if(!authService.checkEmail(email)){
	            request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.EMAIL_ERROR));
	            error=true;
	        } else { 
	        	user.setEmail(email);}
	        
	        if(!authService.checkLogin(login)){
	            request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.LOGIN_ERROR));
	            error=true;
	        } else { 
	        	user.setLogin(login);}
	        
	        if(!authService.checkPassword(password)){
	            request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.PASSWORD_ERROR));
	            error=true;
	        } else { 
	        	user.setPassword(password);}

	        if(error)
	            return Path.REGISTER_PAGE;		    		    
		    
	        else { 
	        	authService.register(user);
		    	request.getSession().setAttribute("message", Messages.getInstance(locale).getString(Messages.REGISTER_SUCCESS));
		       return "redirect:"+Path.LOGIN_PAGE;}
		    	}
}

