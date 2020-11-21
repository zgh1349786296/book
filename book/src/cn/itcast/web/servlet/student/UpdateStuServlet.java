package cn.itcast.web.servlet.student;
import cn.itcast.dao.DAO.StudentDAO;
import cn.itcast.domain.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/UpdateServlet")
public class UpdateStuServlet extends HttpServlet {
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
            String stuName = request.getParameter("sname");
            String stuPassword = request.getParameter("password");
            StudentDAO DAO = new StudentDAO();
            Student student = new Student();
            student.setStuId(stuId);
            student.setStuName(stuName);
            student.setStuPassword(stuPassword);
            //更新学生信息
            try {
                DAO.update(student);
                response.sendRedirect(request.getContextPath()+"/stu_success.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath()+"/stu_fail.jsp");
            }
        } catch (NumberFormatException | IOException e) {
            response.sendRedirect(request.getContextPath()+"/stu_fail.jsp");
            e.printStackTrace();
        }
    }
}
