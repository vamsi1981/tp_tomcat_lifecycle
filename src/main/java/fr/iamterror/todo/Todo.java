package fr.iamterror.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Projet tomcat-lifecycle
 * Pour LAERCE SAS
 * <p>
 * Créé le  28/11/2017.
 *
 * @author fred
 *
 * @author student : IAmTerror
 */
public class Todo extends HttpServlet {
    List<String> todos = new ArrayList<String>();

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        List<String> todoSession = (List<String>) session.getAttribute("todos");
        if(todoSession == null){
            todoSession = new ArrayList<String>();
            session.setAttribute("todos", todoSession);
        }
        String item = httpServletRequest.getParameter("afaire");
        String[] checkbox = httpServletRequest.getParameterValues("global");

        // On vérifie l'état de la checkbox (cochée = global / non cochée = session)
        if (checkbox == null) {
            // On ajoute l'item dans la session et SEULEMENT dans la session
            todoSession.add(item);
            httpServletResponse.sendRedirect("/todo/affiche");
        } else {
            // 1 - On ajoute l'item dans la session ET...
            todoSession.add(item);
            // 2 - ... on vérifie si l'item est déjà présent dans le local et si ce n'est pas le cas...
            if (!todos.contains(item)) {
                // 3 ... on ajoute l'item dans le global
                todos.add(item + " " + httpServletRequest.getRemoteAddr());
                httpServletResponse.sendRedirect("/todo/affiche");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int i;
        HttpSession session = httpServletRequest.getSession();
        List<String> todoSession = (List<String>) session.getAttribute("todos");
        if(todoSession == null){
            todoSession = new ArrayList<String>();
            session.setAttribute("todos", todoSession);
        }
        PrintWriter pw = httpServletResponse.getWriter();
        pw.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Servlet Lifecycle</title>\n" +
                "</head>\n" +
                "<body>\n<h1>Todos</h1>\n");
        pw.println("<h2>Global</h2>\n");
        pw.println("<ul>\n");
        for(i = 0; i < todos.size(); i++){
            pw.println("<li>"+todos.get(i)+"</li>\n");
        }
        pw.println("</ul>\n");
        pw.println("<h2>Session</h2>\n");
        pw.println("<ul>\n");
        for(i = 0; i < todoSession.size(); i++){
            pw.println("<li>"+todoSession.get(i)+"</li>\n");
        }
        pw.println("</ul>\n");
        pw.println("</body>\n</html>");
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
