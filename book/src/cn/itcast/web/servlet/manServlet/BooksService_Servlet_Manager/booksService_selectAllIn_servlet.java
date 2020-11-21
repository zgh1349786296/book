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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BooksInSelectAll")
public class booksService_selectAllIn_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //图书归还记录展示
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        List<BooksService_vo> booksInList = new ArrayList<BooksService_vo>();
        BooksService_dao daoSer = new BooksService_dao();

        books_dao daoBook = new books_dao();
        List<books_vo> bookInfList = new ArrayList<books_vo>();
        List<books_vo> bookList = new ArrayList<books_vo>();

        List<stu_vo> stuInfList = new ArrayList<stu_vo>();
        List<stu_vo> stuList = new ArrayList<stu_vo>();
        stu_dao daoStu = new stu_dao();

        try {
            booksInList = daoSer.booksInSelectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取归还表中记录的同时，根据归还学生学号和图书编号查询相应的信息
        for(int i=0;i<booksInList.size();i++){
            BooksService_vo booksIn = booksInList.get(i);
            String bookId = booksIn.getBookId();
            String stuId = booksIn.getStuId();
            try {
                bookList = daoBook.findBooksById(bookId);
                stuList = daoStu.findStudentById(stuId);
                for(int j=0;j<bookList.size();j++){
                    books_vo book = bookList.get(j);
                    stu_vo stu = stuList.get(j);
                    bookInfList.add(book);
                    stuInfList.add(stu);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("booksInList",booksInList);
        request.setAttribute("bookInfList",bookInfList);
        request.setAttribute("stuInfList",stuInfList);
        request.getRequestDispatcher("/bookIn_select&show.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
