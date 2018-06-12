package chat;

import datastructure.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="ChatServlet", value="/chat")
public class ChatServlet extends HttpServlet {
    private HttpSession session;
    private List<Message> messageList = new ArrayList<>();

    private String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    private List<Message> getMessageList(HttpServletRequest request){
        String message = request.getParameter("messageInput");
        session = request.getSession();
        String username = session.getAttribute("firstName")+ " " + session.getAttribute("lastName");
        messageList.add(new Message(username, message, getDate()));
        return messageList;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!request.getMethod().equals("GET")) {
            messageList = getMessageList(request);
            session.setAttribute("messageList", messageList);
            getServletContext().getRequestDispatcher("/chat.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        if(session.getAttribute("loggedIn")==null) {
            response.sendRedirect("index.jsp");
        }

    }
}
