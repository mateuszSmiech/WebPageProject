package sendingdata;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;

import static org.apache.commons.io.FileUtils.readFileToString;

@WebServlet(name = "SendDataServlet", value="/proceed")
public class SendDataServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        //TODO correct path and funcionality
        URL resource = context.getResource("/../resources/form.html");
        File file = new File(resource.getPath());
        System.out.println(file.getAbsolutePath());

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
