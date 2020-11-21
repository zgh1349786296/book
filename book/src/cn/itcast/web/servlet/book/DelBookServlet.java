package cn.itcast.web.servlet.book;

import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_dao;
import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_vo;
import cn.itcast.domain.Book;
import cn.itcast.domain.BookEdit;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/delBookServlet")
public class DelBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        //2.调用service删除
        Date date = new Date();
        //3.得到管理员id
        String manId="";
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equalsIgnoreCase("manId")) {
                    manId = c.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用服务
        UserService service = new UserServiceImpl();
        Book  book = service.findBookById(id);
        BooksService_dao dao=new BooksService_dao();
        try {
            //查询该图书是否被借
            List<BooksService_vo> books = dao.booksInSelectByBookId(book.getBookId());
            if(books.size()==0&&book!=null){
                //添加操作记录
                BookEdit bookedit =new BookEdit(book.getBookId(),manId,date,"删除",book.getBookCount());
                service.addBookEdit(bookedit);
                //删除图书
                service.deleteBook(id);
                response.sendRedirect(request.getContextPath()+"/bookListServlet");
            }
            else {
                //删除失败
                response.sendRedirect("book_fail.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        //3.跳转到查询所有Servlet

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
