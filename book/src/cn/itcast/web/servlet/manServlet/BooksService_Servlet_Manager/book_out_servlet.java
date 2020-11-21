package cn.itcast.web.servlet.manServlet.BooksService_Servlet_Manager;


import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_dao;
import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_vo;
import cn.itcast.dao.JavaBean.Books_JavaBean.books_dao;
import cn.itcast.dao.JavaBean.Books_JavaBean.books_vo;
import cn.itcast.dao.JavaBean.Student_JavaBean.stu_dao;
import cn.itcast.dao.JavaBean.Student_JavaBean.stu_vo;

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

@WebServlet("/BooksOut")
public class book_out_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String stuId = request.getParameter("stuId");
        String bookId = request.getParameter("bookId");
        int bookCount=0;
        PrintWriter out=response.getWriter();
        books_vo book = new books_vo();
        books_dao booksDao = new books_dao();

        List<stu_vo> stuList = new ArrayList<stu_vo>();
        List<books_vo> booksList = new ArrayList<books_vo>();

        stu_dao studao = new stu_dao();
        try {
            stuList = studao.findStudentById(stuId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            booksList = booksDao.findBooksById(bookId);
            for(int i=0;i<booksList.size();i++){
                book = booksList.get(i);
                bookCount = book.getBookCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断借书的学生是否存在
        if(stuList.size()==0){
            out.print("<script language='javascript'>alert('没有该学生！');window.location.href='SelectAllBooks';</script>");
        }else if(bookCount==0){//判断图书是否有剩余
            out.print("<script language='javascript'>alert('没有剩余图书！');window.location.href='SelectAllBooks';</script>");
        }else{//条件满足时进行借阅
            BooksService_vo bookSer = new BooksService_vo();
            BooksService_dao bookSerDao = new BooksService_dao();
            bookSer.setBookId(bookId);
            bookSer.setStuId(stuId);
            bookSer.setTime(new Timestamp(System.currentTimeMillis()));
            try {
                bookSerDao.bookOut(bookSer);
                out.print("<script language='javascript'>alert('借阅成功！');window.location.href='SelectAllBooks';</script>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
