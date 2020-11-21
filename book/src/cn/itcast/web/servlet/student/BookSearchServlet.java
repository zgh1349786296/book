package cn.itcast.web.servlet.student;

import cn.itcast.dao.DAO.BookDAO;
import cn.itcast.domain.Book;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allBookSearchServlet")
public class BookSearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookName = request.getParameter("bookName");
        String bookClassify = request.getParameter("bookClassify");
        BookDAO DAO = new BookDAO();
        List<Book> books = null;
        try {
            books = DAO.search(bookName, bookClassify);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("books",books);
        try {
            request.getRequestDispatcher("/stu_searchallbook.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
