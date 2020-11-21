package cn.itcast.web.servlet.manServlet.Books_Servlet_Manager;


import cn.itcast.dao.JavaBean.Books_JavaBean.books_dao;
import cn.itcast.dao.JavaBean.Books_JavaBean.books_vo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/SelectBooks")
public class book_select_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String operation=request.getParameter("action");
        List<books_vo> bookList = new ArrayList<books_vo>();
        books_dao dao = new books_dao();
        PrintWriter out=response.getWriter();

        //判断是否是进行借阅，不是则正常按类型搜索
        if(operation==null){
            String findInf=request.getParameter("find");//查询的值
            String condition=request.getParameter("select_condition");
            try {
                if(condition.equals("图书编号")){
                    bookList=dao.findBooksById(findInf);
                }else if(condition.equals("图书名称")){
                    bookList=dao.findBooksByName(findInf);
                }
                else if(condition.equals("图书类型")){
                    bookList=dao.findBooksByClassify(findInf);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(bookList.size()==0){
                out.print("<script language='javascript'>alert('无搜索结果！');window.location.href='SelectAllBooks';</script>");
            }else if(bookList.size()!=0){
                request.setAttribute("bookList",bookList);
                request.getRequestDispatcher("/book_select&show.jsp").forward(request,response);
            }
        }

        //若有借阅操作，按id查询后跳转至相应页面借阅书籍
        if(operation!=null){
            String id = request.getParameter("bookId");
            try {
                bookList = dao.findBooksById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(operation.equals("Out")){
                request.setAttribute("bookList",bookList);
                request.getRequestDispatcher("/book_Out.jsp").forward(request,response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
