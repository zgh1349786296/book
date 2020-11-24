package cn.itcast.web.servlet;

import cn.itcast.domain.Manager;
import cn.itcast.domain.Student;
import cn.itcast.domain.User;
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
import java.util.Map;

@WebServlet("/mloginServlet")
public class MloginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        //得到按钮值以及输入的账号密码
        request.setCharacterEncoding("utf-8");
        String sub_name=request.getParameter("sub_name");
        String id=request.getParameter("userId");
        String password=request.getParameter("userPassword");

        UserService service = new UserServiceImpl();
        //若点击用户登录
        if(sub_name.equals("用户登录")){
            Student student = new Student();
            student.setStuId(id);
            student.setStuPassword(password);
            Student loginstudent = service.mlogin(student);
            //判断是否存在该用户
            if(loginstudent != null){
                //利用cookie保存用户信息
                Cookie logininf = new Cookie("stuId", id);
                logininf.setMaxAge(60*60);
                logininf.setPath("/");
                response.addCookie(logininf);
                request.getRequestDispatcher("stu_booklist.jsp").forward(request,response);

            }else{
                //登录失败
                //提示信息
                request.setAttribute("login_msg","用户名或密码错误！");
                //跳转登录页面
                request.getRequestDispatcher("/mlogin.jsp").forward(request,response);
            }
        }
        //若点击用户登录
        else if(sub_name.equals("管理员登录")){
            Manager manager = new Manager();
            manager.setManId(id);
            manager.setManPassword(password);
            Manager loginmanagerr = service.login(manager);
            //判断是否存在该用户
            if(loginmanagerr != null){
                //利用cookie保存管理员信息
                Cookie logininf = new Cookie("manId", id);
                request.setAttribute("man",manager);
                request.setAttribute("manId",manager.getManId());
                response.addCookie(logininf);
                request.getRequestDispatcher("/TotalPage.jsp").forward(request,response);
            }else{
                //登录失败
                //提示信息
                request.setAttribute("login_msg","用户名或密码错误！");
                //跳转登录页面
                request.getRequestDispatcher("/mlogin.jsp").forward(request,response);
            }
        }




    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
