package cn.itcast.web.servlet.book;

import cn.itcast.domain.*;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/findBookByIdServlet")
public class FindBookByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//1.获取id
        String id = request.getParameter("id");

        //2.调用Service查询

        UserService service = new UserServiceImpl();
        Book book = service.findBookById(id);
    //添加操作记录
        if(book==null){
            response.sendRedirect("book_fail.jsp");
        }
        else {
            Date date = new Date();
            BookEdit bookedit = new BookEdit(book.getBookId(), "zgh", date, "查找", book.getBookCount());
            service.addBookEdit(bookedit);
            request.setAttribute("book", book);
            //4.实现返现.jsp
            request.getRequestDispatcher("/book_update.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
