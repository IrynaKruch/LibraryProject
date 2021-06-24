package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.exception.NotUniqueUserException;
import ua.krucheniuk.service.AuthService;

public class UpdateUserCommand implements Command {

	private static final Logger log = Logger.getLogger(UpdateUserCommand.class);

	AuthService authService;

	public UpdateUserCommand(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String locale = (String) request.getSession().getAttribute("locale");
		User sessionUser = (User) request.getSession().getAttribute("sessionUser");
		User user = sessionUser;
		try {
			user = (User) sessionUser.clone();
		} catch (CloneNotSupportedException e) {
			log.info(e.getMessage());
		}
		String fullname = request.getParameter("fullname");
		if (fullname != null & authService.checkName(fullname))
			user.setFullname(fullname);
		String email = request.getParameter("email");
		if (email != null & authService.checkEmail(email))
			user.setEmail(email);
		String login = request.getParameter("login");
		if (login != null & authService.checkLogin(login))
			user.setLogin(login);
		String password = request.getParameter("password");
		if (password != null & authService.checkPassword(password))
			user.setPassword(password);
		try {
			authService.updateUser(user);
		} catch (NotUniqueUserException ex) {
			log.info(ex.getMessage());
			request.getSession().setAttribute("errMessage",
					Messages.getInstance(locale).getString(Messages.NOT_UNIQUE) + ex.getLoginData());
			return "redirect:" + Path.PERSONAL_PAGE;
		}
		request.getSession().setAttribute("message", Messages.getInstance(locale).getString(Messages.UPDATE_USER));
		request.getSession().setAttribute("sessionUser", user);

		return "redirect:" + Path.PERSONAL_PAGE;
	}

}
