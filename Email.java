package mail.Servlet;
 
import static mail.Servlet.Mail.sendMail;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class Email extends HttpServlet {
 
    protected void
    processRequest(HttpServletRequest request,
                   HttpServletResponse response)
        throws ServletException, IOException
    {
 
        String id, sender, receiver, password, subject,
            message;
 
        // check if directed url
        String action = request.getParameter("action");
        String url = "/Lekcja_41_42_43_44_45.html";
        if (action == null) {
            // directed to email interface
            action = "join";
        }
        if (action.equals("join")) {
            url = "/Lekcja_41_42_43_44_45.html";
        }
        if (action.equals("add")) {
            // retrieve the entered credentials
            id = request.getParameter("id");
            sender = request.getParameter("emailSender");
            receiver
                = request.getParameter("emailReceiver");
            password = request.getParameter("password");
            subject = request.getParameter("subject");
            message = request.getParameter("message");
            // get and set String value of email status
            request.setAttribute(
                "message",
                sendMail(id, receiver, sender, subject,
                         message, true, password));
            // directed to page showing the status of email
            url = "/confirmation.jsp";
        }
 
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }
 
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest(request, response);
    }
 
    // Returns a short description of the servlet.
    // @return a String containing servlet description
    @Override public String getServletInfo()
    {
        return "Short description";
    }
}