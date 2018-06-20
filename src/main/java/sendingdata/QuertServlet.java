package sendingdata;


import datastructure.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryServlet", value="/query")
public class QuertServlet extends HttpServlet {

    private SessionFactory sessionFactory;

    public QuertServlet(SessionFactory sessionFactory) {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        Query query = session.createQuery("SELECT s FROM User s");

        List<User> list  = query.list();

        trx.commit();
        session.close();
    }
}
