package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.AuthService;
import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;

public class LoginCommand implements Command {

	private final static Logger log = Logger.getLogger(LoginCommand.class);

	AuthService authService;

	public LoginCommand(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String locale = request.getParameter("locale");

		if (!authService.checkLogin(login) || !authService.checkPassword(password)) {
			request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.LOG_ERROR));
			return Path.LOGIN_PAGE;
		}

		if (CommandUtility.checkUserIsLogged(request, login)) {
			request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.LOGIN_REPEAT));
			return Path.ERROR_PAGE;
		}

		User user = authService.login(login, password);
		if (user != null) {
			CommandUtility.setAttributes(request, user);
			return "redirect:" + Path.HOME_PAGE;
		} else {
			log.error(user);
			CommandUtility.logout(request, login);
			request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.LOG_ERROR));
			return Path.ERROR_PAGE;
		}
	}
}
