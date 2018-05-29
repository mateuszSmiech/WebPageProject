package filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AgentFilter", servletNames = {"LoginServlet"})
public class AgentFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String serverInfo = request.getServletContext().getServerInfo();
        filterChain.doFilter(request, response);
        ServletContext context = request.getServletContext();
        context.log(serverInfo);
    }

    @Override
    public void destroy() {

    }
}
