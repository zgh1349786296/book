package cn.itcast.web.servlet.manServlet.BooksService_Servlet_Manager;




import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_dao;
import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_vo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BooksIn")
public class book_in_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String stuId = request.getParameter("stuId");
        String bookId = request.getParameter("bookId");
        Timestamp time = Timestamp.valueOf(request.getParameter("time"));

        BooksService_dao booksServiceDao = new BooksService_dao();
        List<BooksService_vo> bookOutList = new ArrayList<BooksService_vo>();
        PrintWriter out=response.getWriter();

        BooksService_vo booksServiceVo = new BooksService_vo(stuId,bookId,time);

        try {
            bookOutList = booksServiceDao.booksOutSelect(booksServiceVo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断借阅表里是否有借阅记录，如果有则归还，没有则提示不存在
        if(bookOutList.size()!=0){
            try {
                booksServiceDao.bookOutDelete(booksServiceVo);
                booksServiceVo.setTime(new Timestamp(System.currentTimeMillis()));
                booksServiceDao.bookIn(booksServiceVo);
                out.print("<script language='javascript'>alert('归还成功！');window.location.href='BooksOutSelectAll';</script>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            out.print("<script language='javascript'>alert('借阅记录不存在！');window.location.href='BooksOutSelectAll';</script>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
