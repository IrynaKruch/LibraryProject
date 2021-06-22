package ua.krucheniuk.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.controller.command.Command;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 6463664323365661579L;
    private final static Logger log = Logger.getLogger(Servlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		String path = request.getRequestURI();
        path = path.replaceAll(".*/LibraryMS/" , "");
        Command command = ServletHelper.get(path);
		
		String page = command.execute(request, response);
		if (page.contains("redirect:")) {
			response.sendRedirect(page.replace("redirect:", "/LibraryMS"));
		} else {
			request.getRequestDispatcher(page).forward(request, response);     
		}
		} catch (ServletException | IOException ex) {
			log.error(ex.getMessage());
		}
	}
}
