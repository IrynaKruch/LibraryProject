package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	String USER = "sessionUser";
	
    String execute(HttpServletRequest request, HttpServletResponse response);
}
