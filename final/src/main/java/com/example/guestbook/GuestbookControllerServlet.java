package com.example.guestbook;

import com.example.guestbook.entity.Greeting;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestbookControllerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String guestbookName = request.getParameter("guestbookName");
		if (guestbookName == null) {
			guestbookName = "default";
		}
		request.setAttribute("guestbookName", guestbookName);

		// Service d'authentification Google
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		request.setAttribute("user", user);

		String logoutURL = userService.createLogoutURL(request.getRequestURI());
		request.setAttribute("logoutURL", logoutURL);

		String loginURL = userService.createLoginURL(request.getRequestURI());
		request.setAttribute("loginURL", loginURL);

		GuestBookDAO dao = new GuestBookDAO();
		try {
			String action = request.getParameter("action");
			if ("addGreeting".equals(action)) {
				String content = request.getParameter("content");
				String authorId = (user == null) ? "Unknown author" : user.getUserId();
				String authorEmail = (user == null) ? "Unknown email" : user.getEmail();
				dao.addGreeting(guestbookName, authorId, authorEmail, content);

			}
			List<Greeting> greetings = dao.findGreetingsIn(guestbookName);
			request.setAttribute("greetings", greetings);
			List<String> books = dao.existingBooks();
			request.setAttribute("books", books);			
		} catch (SQLException | ServletException ex) {
			Logger.getLogger(GuestbookControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		request.getRequestDispatcher("guestbookView.jsp").forward(request, response);
	}
}
