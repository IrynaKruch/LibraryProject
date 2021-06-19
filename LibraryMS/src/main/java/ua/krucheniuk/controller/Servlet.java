package ua.krucheniuk.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.controller.command.Command;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 6463664323365661579L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getRequestURI();
        path = path.replaceAll(".*/LibraryMS/" , "");
        Command command = ServletHelper.get(path);

		

//		Command command = ServletHelper.get(request);
		String page = command.execute(request, response);
		if (page.contains("redirect:")) {
			response.sendRedirect(page.replace("redirect:", "/LibraryMS"));
		} else {
			request.getRequestDispatcher(page).forward(request, response);     
		}
	}

//        //Copying all the input parameters in to local variables
//         String fullName = request.getParameter("fullname");
//         String email = request.getParameter("email");
//         String userName = request.getParameter("username");
//         String password = request.getParameter("password");
//         
//         RegisterBean registerBean = new RegisterBean();
//        //Using Java Beans - An easiest way to play with group of related data
//         registerBean.setFullName(fullName);
//         registerBean.setEmail(email);
//         registerBean.setUserName(userName);
//         registerBean.setPassword(password); 
//         
//         RegisterDao registerDao = new RegisterDao();
//         
//        //The core Logic of the Registration application is present here. We are going to insert user data in to the database.
//         String userRegistered = registerDao.registerUser(registerBean);
//         
//         if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
//         {
//            request.getRequestDispatcher("/Home.jsp").forward(request, response);
//         }
//         else   //On Failure, display a meaningful message to the User.
//         {
//            request.setAttribute("errMessage", userRegistered);
//            request.getRequestDispatcher("/register.jsp").forward(request, response);
//         }
//     }
//	if(page!=null) {
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//		dispatcher.forward(request, response);

}
