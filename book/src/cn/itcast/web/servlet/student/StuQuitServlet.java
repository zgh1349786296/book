package cn.itcast.web.servlet.student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StuQuitServlet")
public class StuQuitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //清除cookie
        Cookie logininf = new Cookie("stuId", null);
        logininf.setMaxAge(0);
        logininf.setPath("/");
        response.addCookie(logininf);
        response.sendRedirect(request.getContextPath()+"/mlogin.jsp");
    }
}
