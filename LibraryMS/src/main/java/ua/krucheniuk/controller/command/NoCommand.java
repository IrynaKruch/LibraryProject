package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;

public class NoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String locale = (String) request.getSession().getAttribute("locale");
		request.setAttribute("errMessage", Messages.getInstance(locale).getString(Messages.NO_COMMAND));
		return Path.ERROR_PAGE;
	}

}
