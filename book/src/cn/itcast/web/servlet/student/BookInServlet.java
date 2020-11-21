package cn.itcast.web.servlet.student;

import cn.itcast.dao.DAO.BookDAO;
import cn.itcast.domain.Book;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/bookInServlet")
public class BookInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String stuId = request.getParameter("stuId");
            String bookId = request.getParameter("bookId");
            Timestamp time = Timestamp.valueOf(request.getParameter("time"));
            BookDAO DAO = new BookDAO();
            Book book = new Book(bookId);
            //还书
            try {
                String note = DAO.bookin(book,stuId,time);
                if(note.equals("success")){
                    response.sendRedirect(request.getContextPath()+"/stu_success.jsp");//用于跳出框架跳转页面
                }
                else{
                    response.sendRedirect(request.getContextPath()+"/stu_fail.jsp");//用于跳出框架跳转页面
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath()+"/stu_fail.jsp");//用于跳出框架跳转页面
            }
        }
}
