package ua.krucheniuk.listener;

import java.util.HashSet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ua.krucheniuk.entity.User;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        User user = (User) httpSessionEvent.getSession()
                .getAttribute("user");
        loggedUsers.remove(user.getLogin());
        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

}
