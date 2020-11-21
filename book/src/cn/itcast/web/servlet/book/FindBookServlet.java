package cn.itcast.web.servlet.book;

import cn.itcast.domain.Book;
import cn.itcast.domain.BookEdit;
import cn.itcast.domain.Manager;
import cn.itcast.domain.Student;
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

@WebServlet("/findBookServlet")
public class FindBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //获取下拉菜单值
        String find_name=request.getParameter("bookFind");
        //获取查询信息值
        String find_inf=request.getParameter("book");
        UserService service = new UserServiceImpl();
        Date date = new Date();
        //获取管理员id
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


        if(find_name.equals("图书编号")){
        //下拉菜单为图书编号查询该图书
        Book book =service.findBookById(find_inf);
            if(book==null){
                //书为空
                request.setAttribute("login_msg","该书不存在");
                //添加操作记录
                BookEdit bookedit =new BookEdit("0",manId,date,"查找",0);
                service.addBookEdit(bookedit);
                request.getRequestDispatcher("/bookListServlet").forward(request,response);
            }
            else{
                //书存在
                //添加操作记录
                BookEdit bookedit =new BookEdit(book.getBookId(),manId,date,"查找",book.getBookCount());
                service.addBookEdit(bookedit);
                request.setAttribute("book",book);
                //存入request 返现
                request.getRequestDispatcher("/book_update.jsp").forward(request,response);
                //返回参数
            }

        }
        else if(find_name.equals("书名")){
            //以书名查询
            List<Book> books = service.findBookByName(find_inf);
            //插入操作记录
            BookEdit bookedit =new BookEdit("0",manId,date,"查找",books.size());
            service.addBookEdit(bookedit);
            if(books.size()==0){
                request.setAttribute("login_msg","该书不存在");
                //查询不到该书
                request.getRequestDispatcher("/bookListServlet").forward(request,response);
            }
            else{
                //2.将list存入request域
                request.setAttribute("books",books);
                //3.转发到list.jsp
                request.getRequestDispatcher("/book_list.jsp").forward(request,response);
            }

        }

        else if(find_name.equals("图书类别")){
            //按图书类别查询
            List<Book> books = service.findBookByClassify(find_inf);
            //插入操作记录
            BookEdit bookedit =new BookEdit("0",manId,date,"查找",books.size());
            service.addBookEdit(bookedit);
            if(books.size()==0){
                request.setAttribute("login_msg","该类不存在");
                //图书不存在提示
                request.getRequestDispatcher("/bookListServlet").forward(request,response);
            }
            else{
                //2.将list存入request域
                request.setAttribute("books",books);
                //3.转发到book_list.jsp
                request.getRequestDispatcher("/book_list.jsp").forward(request,response);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
