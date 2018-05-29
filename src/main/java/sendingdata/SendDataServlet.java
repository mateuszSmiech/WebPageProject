package sendingdata;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Enumeration;

import static org.apache.commons.io.FileUtils.readFileToString;

@WebServlet(name = "SendDataServlet", value="/proceed")
public class SendDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File("C:\\Users\\Mateusz\\IdeaProjects\\passwordGenerator\\src\\/main/resorces/form.html");
        String s = readFileToString(file, Charset.defaultCharset());
        PrintWriter out = resp.getWriter();
        out.println(s);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        PrintWriter out = response.getWriter();
        while (parameterNames.hasMoreElements()) {
            String param = parameterNames.nextElement();
            out.println("FirstInput: "+ param + ", value: "+ request.getParameter(param));
        }

    }
}
