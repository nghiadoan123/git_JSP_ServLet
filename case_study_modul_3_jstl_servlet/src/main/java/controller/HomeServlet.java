package controller;

import service.ILoginService;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    ILoginService iLoginService = new LoginServiceImpl();
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // sau khi tạo sesion sau khi set attribute bên phía login thì bên trang home chỉ
        // cần lấy thông tin vì tất cả t tin đã để trên đám mây
        HttpSession session = request.getSession();
        String username =  session.getAttribute("usernameinfo").toString();
        String password =  session.getAttribute("passwordinfo").toString();

        request.getRequestDispatcher("pages/home/home.jsp").forward(request,response);
    }
}
