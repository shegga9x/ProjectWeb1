package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import database.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		try {
			User user = new User();
			if (request.getParameter("pw").equals(request.getParameter("pw1")) && !request.getParameter("un").equals("")) {
				user.setUserName(request.getParameter("un"));
				user.setPassword(request.getParameter("pw"));
				user = UserDAO.register(user);
				if (user.isValid() == false ) {
					HttpSession session = request.getSession(true);
					session.setAttribute("currentSessionUser", user);
					response.sendRedirect("login.html"); // logged-in page
					System.out.println("dung r");
				}
				else {
					response.sendRedirect("login.html");
				}
			} else {
				response.sendRedirect("login.html");
				System.out.println("sai pass r");
			}
		}

		catch (Throwable theException) {
			response.sendRedirect("register.html"); // error page
			System.out.println(theException);
		}
	}
}
