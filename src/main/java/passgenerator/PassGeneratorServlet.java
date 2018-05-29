package passgenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "PassGeneratorServlet", value = "/generate")
public class PassGeneratorServlet extends HttpServlet {
    private Random rand = new Random();

    private String generateUpperLetter(HttpServletRequest request) {
        if(request.getParameter("incUpper") == null) {
            return null;
        } else {
            int range = rand.nextInt(90 - 65 + 1) + 65;
            char letter = (char) range;
            return Character.toString(letter);
        }
    }

    private String generateLowerLetter(HttpServletRequest request) {
        if(request.getParameter("incLower")== null){
            return null;
        } else {
            int range = rand.nextInt(122 - 97 + 1) + 97;
            char lowerLetter = (char) range;
            return Character.toString(lowerLetter);
        }

    }

    private String generateSpecialChar(HttpServletRequest request) {
        if (request.getParameter("incSymbols") == null) {
            return null;
        } else {
            String[] specialLetter = {"@", "#", "$", "/", "%", "&", "|", ">", "?", "<", "-", "_"};
            int a = rand.nextInt(6);
            return specialLetter[a];
        }
    }

    private Integer generateNumber(HttpServletRequest request) {
        if(request.getParameter("incNumber")== null) {
            return null;
        } else {
            return rand.nextInt(9);
        }
    }

    private String getFinalPassword(Integer counter ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random shuffle = new Random();
        StringBuilder a = new StringBuilder();
        for (int i = 0; i< counter; i++) {
            List<String> stringList = new ArrayList<>();
            if (generateUpperLetter(request)!=null) {
                stringList.add(generateUpperLetter(request));
            }
            if (generateLowerLetter(request)!=null) {
                stringList.add(generateLowerLetter(request));
            }
            if (generateSpecialChar(request)!=null) {
                stringList.add(generateSpecialChar(request));
            }
            if (generateNumber(request)!=null) {
                stringList.add(String.valueOf(generateNumber(request)));
            }
            if(stringList.isEmpty()) {
                break;
            }

            int lot = shuffle.nextInt(stringList.size());
            a.append(stringList.get(lot));
        }

        return a.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer counter = Integer.parseInt(request.getParameter("passLength"));
        String finalPassword = getFinalPassword(counter, request, response);

        request.setAttribute("result2", finalPassword);
        getServletContext().getRequestDispatcher("/generator.jsp").forward(request, response);
    }
}
