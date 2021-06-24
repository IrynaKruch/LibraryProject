package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.exception.NotUniqueUserException;
import ua.krucheniuk.service.AuthService;

public class RegisterCommand implements Command {

	private static final Logger log = Logger.getLogger(RegisterCommand.class);

	AuthService authService;

	public RegisterCommand(AuthService authService) {
		this.authService = authService;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String locale = (String) request.getSession().getAttribute("locale");
		String name = request.getParameter("fullname");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		User user = new User();
		if (name == null || !authService.checkName(name)) {
			log.info(name);
			request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.NAME_ERROR));
			return Path.REGISTER_PAGE;
		} else {
			user.setFullname(name);
		}

		if (email == null || !authService.checkEmail(email)) {
			log.info(email);
			request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.EMAIL_ERROR));
			return Path.REGISTER_PAGE;
		} else {
			user.setEmail(email);
		}

		if (login == null || !authService.checkLogin(login)) {
			log.info(login);
			request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.LOGIN_ERROR));
			return Path.REGISTER_PAGE;
		} else {
			user.setLogin(login);
		}

		if (password == null || !authService.checkPassword(password)) {
			request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.PASSWORD_ERROR));
			return Path.REGISTER_PAGE;
		} else {
			user.setPassword(password);
		}

		try {
			authService.register(user);
		} catch (NotUniqueUserException ex) {
			log.info(ex.getMessage());
			request.getSession().setAttribute("errMessage",
					Messages.getInstance(locale).getString(Messages.NOT_UNIQUE) + " " + ex.getLoginData());
			return "redirect:" + Path.REGISTER_PAGE;
		}
		request.getSession().setAttribute("message", Messages.getInstance(locale).getString(Messages.REGISTER_SUCCESS));
		return "redirect:" + Path.LOGIN_PAGE;

	}

}
