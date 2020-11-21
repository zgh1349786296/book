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

@WebServlet("/addBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Book book = new Book();
        try {
            BeanUtils.populate(book,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用服务
        UserService service = new UserServiceImpl();
        Book exitbook = service.findBookById(book.getBookId());
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

        //查询书id是否存在
        if(exitbook==null){
            BookEdit bookedit =new BookEdit(book.getBookId(),manId,date,"增加",book.getBookCount());
            //添加书
            service.addBook(book);
            //添加操作记录
            service.addBookEdit(bookedit);
            //跳转到操作成功界面
            response.sendRedirect("book_success.jsp");
        }
        else{
            //书存在添加失败
            request.setAttribute("add_msg","该书已存在");
            request.getRequestDispatcher("/book_add.jsp").forward(request,response);

        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            this.doPost(request, response);
    }
}
