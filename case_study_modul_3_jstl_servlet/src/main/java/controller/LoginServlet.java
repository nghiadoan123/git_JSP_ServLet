package controller;

import bean.user.Login;
import service.IEmployeeService;
import service.ILoginService;
import service.impl.EmployServiceImpl;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"", "/login"})
public class LoginServlet extends HttpServlet {
    ILoginService iLoginService = new LoginServiceImpl();
    IEmployeeService iEmployeeService = new EmployServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        if (iLoginService.login(login)) {


            // phần sesion bỏ dữ liệu từ usernameinfo hiển thị trong trang home
            HttpSession session = request.getSession();
            session.setAttribute("usernameinfo", username);
            session.setAttribute("passwordinfo", password);


            // phần lưu cookie
            Cookie u = new Cookie("userC",username);
            Cookie p = new Cookie("passwordC",password);
            u.setMaxAge(60);
            p.setMaxAge(60);
            response.addCookie(u);// lưu username vào trong cookie
            response.addCookie(p);// lưu password vào trong cookie

//            request.getRequestDispatcher("pages/home/home.jsp").forward(request, response);
            response.sendRedirect("/home");

        } else {
            request.setAttribute("messageLogin", "incorrect username or pass word");
            response.sendRedirect("/index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("pages/login/login.jsp").forward(request, response);
    }
}
