package cn.itcast.web.servlet.student;

import cn.itcast.dao.DAO.BookDAO;
import cn.itcast.dao.DAO.BookRecorderDAO;
import cn.itcast.domain.Book;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookOutServlet")
public class BookOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //得到学生id
        try {
            String stuId = null;
            try {
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    if (c.getName().equalsIgnoreCase("stuId")) {
                        stuId = c.getValue();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String bookId = request.getParameter("bookId");
            BookDAO dao = new BookDAO();
            BookRecorderDAO recorderDAO=new BookRecorderDAO();
            Book book = new Book(bookId);
            //得到图书数量
            int count = recorderDAO.bookOutCount(stuId);
            if(count<5){
                try {

                    String note = dao.bookout(book,stuId);
                    if(note.equals("success")){
                        response.sendRedirect(request.getContextPath()+"/stu_success.jsp");//用于跳出框架跳转页面
                    }else{
                        response.sendRedirect(request.getContextPath()+"/stu_fail.jsp");//用于跳出框架跳转页面
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath()+"/stu_fail.jsp");//用于跳出框架跳转页面
                }
            }
            else{
                response.sendRedirect(request.getContextPath()+"/stu_fail_bookout.jsp");//用于跳出框架跳转页面
            }

        } catch (NumberFormatException | IOException e) {
            response.sendRedirect(request.getContextPath()+"/stu_fail.jsp");//用于跳出框架跳转页面
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
