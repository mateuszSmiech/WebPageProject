package loginmanager;

import datastructure.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private boolean isLoggedIn = false;
    HttpSession session;
    private User invalidUser = new User("","","","");

    private List<User> getUserList() {
        User user1 = new User();
        user1.setFirstName("Mateusz");
        user1.setLastName("Smiech");
        user1.setUsername("mateusz123");
        user1.setPassword("qwerty");
        user1.setEmail("Email@gmail.com");
        User user2 = new User();
        user2.setFirstName("Andrzej");
        user2.setLastName("Smiech");
        user2.setUsername("andrzej321");
        user2.setPassword("qwerty");
        user2.setEmail("Email2@gmail.com");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    private User findByUsername(List<User> usersList, HttpServletRequest request) {
        for (User users : usersList) {
            if (request.getParameter("loginInput").equals(users.getUsername())) {
                return users;
            }
        }
        return invalidUser;
    }

    private User findByPassword(List<User> userList, HttpServletRequest request) {
        for (User users : userList) {
            if (request.getParameter("passwordInput").equals(users.getPassword())) {
                return users;
            }
        }
        return invalidUser;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = getUserList();

        if (!(request.getParameter("loginInput").equals("")) || !(request.getParameter("passwordInput").equals(""))) {

                if (findByUsername(userList, request).getUsername().equals(request.getParameter("loginInput"))
                        && findByPassword(userList, request).getPassword().equals(request.getParameter("passwordInput"))) {

                        isLoggedIn = true;
                        session = request.getSession();
                        session.setAttribute("loggedIn", isLoggedIn);
                        session.setAttribute("firstName", findByUsername(userList, request).getFirstName());
                        session.setAttribute("lastName", findByUsername(userList, request).getLastName());
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
