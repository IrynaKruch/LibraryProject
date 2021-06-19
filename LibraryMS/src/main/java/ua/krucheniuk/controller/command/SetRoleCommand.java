package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.AuthService;
import ua.krucheniuk.service.LibrarianService;

public class SetRoleCommand implements Command {

	AuthService authService = AuthService.getInstance();
	LibrarianService librarianService = LibrarianService.getInstance();
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = librarianService.findUserById(Integer.parseInt(request.getParameter("userId")));
		user.setRole(request.getParameter("role"));
		
		authService.update(user);
		
		return Path.READERS_COMMAND;
	}

}
