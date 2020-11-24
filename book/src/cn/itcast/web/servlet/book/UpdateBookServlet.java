package cn.itcast.web.servlet.book;

import cn.itcast.domain.*;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

@WebServlet("/updateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Book book = new Book();
        Date date = new Date();
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
        try {
            BeanUtils.populate(book,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //插入操作记录
        UserService service = new UserServiceImpl();
        BookEdit bookedit =new BookEdit(book.getBookId(),manId,date,"修改",book.getBookCount());
        //更新图书
        Book exitbook = service.findBookById(book.getBookId());
        if(exitbook==null) {
            response.sendRedirect("book_fail.jsp");
        }
        else {
            if (book.getBookCount() < 0) {
                response.sendRedirect("book_fail.jsp");
            } else {

                service.updateBook(book);
                service.addBookEdit(bookedit);

                //5.跳转更新成功
                response.sendRedirect("book_success.jsp");
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
