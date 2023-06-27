import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username= request.getParameter("user");
        String password= request.getParameter("password");

      //  if(username.equals("admin") && password.equals("password")){
         //  if (isValidUsername(username) && password.equals("password")) {
               if (isValidPassword(password) && username.equals("admin")) {

            HttpSession session= request.getSession();
            session.setAttribute("username",username);
            response.sendRedirect("LoginSuccess.jsp");
        } else {
            RequestDispatcher requestDispatcher= getServletContext().getRequestDispatcher("/Login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Incorrect Credential</font>");
            requestDispatcher.include(request,response);
        }
    }
    private boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z]{3,}$"); // Regex pattern for name validation
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

}}
