package cn.itcast.web.servlet.manServlet.Books_Servlet_Manager;


import cn.itcast.dao.JavaBean.Books_JavaBean.books_dao;
import cn.itcast.dao.JavaBean.Books_JavaBean.books_vo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/SelectAllBooks")
public class book_selectAll_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        List<books_vo> bookList = new ArrayList<books_vo>();
        books_dao dao = new books_dao();

        //查询并显示所有图书
        try {
            bookList = dao.QueryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher("/book_select&show.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
