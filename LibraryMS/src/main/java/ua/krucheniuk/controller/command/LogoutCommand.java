package ua.krucheniuk.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;

public class LogoutCommand implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Optional<User> user = Optional.ofNullable((User) session.getAttribute("sessionUser"));

		if (user.isPresent()) {
			CommandUtility.logout(request, user.get().getLogin());
			session.removeAttribute("sessionUser");}
		
		if (session != null)
				session.invalidate();

			return Path.LOGIN_PAGE;
		}
	

}
