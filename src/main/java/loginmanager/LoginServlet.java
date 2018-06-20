package loginmanager;

import datastructure.User;
import services.UsersServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UsersServices usersServices = new UsersServices();
    private User invalidUser = new User("","","","");


    private User findByUsername(HttpServletRequest request) {
        if(usersServices.findByUsername(request.getParameter("loginInput")) != null){
                return usersServices.findByUsername(request.getParameter("loginInput"));
            }
        return invalidUser;
    }

    private User findByPassword(HttpServletRequest request) {
        if(usersServices.findByPassword(request.getParameter("passwordInput")) != null) {
            return usersServices.findByPassword(request.getParameter("passwordInput"));
        }
        return invalidUser;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;
        if (!(request.getParameter("loginInput").equals("")) || !(request.getParameter("passwordInput").equals(""))) {
                if (findByUsername(request).getUsername().equals(request.getParameter("loginInput"))
                        && findByPassword(request).getPassword().equals(request.getParameter("passwordInput"))) {

                    boolean isLoggedIn = true;
                        session = request.getSession();
                        session.setAttribute("loggedIn", isLoggedIn);
                        session.setAttribute("firstName", findByUsername(request).getFirstName());
                        session.setAttribute("lastName", findByUsername(request).getLastName());
                        session.setMaxInactiveInterval(60*60*24);
                        response.sendRedirect("dashboard.jsp");

                } else {
                    String wrongData = "Login or Password is invalid.";
                    session = request.getSession();
                    session.setAttribute("invalidLogin", wrongData);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

                }
        } else {
            String emptyLogin = "Enter all required data.";
            session = request.getSession();
            session.setAttribute("invalidLogin", emptyLogin);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
