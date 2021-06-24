package ua.krucheniuk.controller.command;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.krucheniuk.entity.User;

public class CommandUtility {
	static void setAttributes(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute("sessionUser", user);
	}

	@SuppressWarnings("unchecked")
	static boolean checkUserIsLogged(HttpServletRequest request, String login) {
		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");

		if (loggedUsers == null) {
			loggedUsers = new HashSet<>();
			loggedUsers.add(login);
			request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
			return false;
		}
		if (loggedUsers.stream().anyMatch(login::equals)) {
			return true;
		}
		loggedUsers.add(login);
		request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
		return false;
	}

	@SuppressWarnings("unchecked")
	static boolean logout(HttpServletRequest request, String login) {
		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");
		if (loggedUsers == null) {
			return false;
		}
		if (loggedUsers.stream().anyMatch(login::equals)) {
			loggedUsers.remove(login);
			request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
			return true;
		}
		return false;
	}
}
