package cn.itcast.web.servlet.bookoperat;

import cn.itcast.domain.Book;
import cn.itcast.domain.BookEdit;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bookEditListServlet")
public class BookEditListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserServiceImpl();
        List<BookEdit> bookedits = null;
        try {
            bookedits = service.findAllBookEdit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.将list存入request域
        request.setAttribute("bookedits",bookedits);
        //3.转发到list.jsp
        request.getRequestDispatcher("/bookedit_list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
