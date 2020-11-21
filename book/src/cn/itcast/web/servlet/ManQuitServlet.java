package cn.itcast.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManQuitServlet")
public class ManQuitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //清除cookie
        Cookie logininf = new Cookie("manId", null);
        logininf.setMaxAge(0);

        response.addCookie(logininf);
        response.sendRedirect(request.getContextPath()+"/mlogin.jsp");
    }
}
