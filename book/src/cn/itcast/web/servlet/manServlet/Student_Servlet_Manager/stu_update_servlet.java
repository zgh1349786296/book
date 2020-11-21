package cn.itcast.web.servlet.manServlet.Student_Servlet_Manager;



import cn.itcast.dao.JavaBean.Student_JavaBean.stu_dao;
import cn.itcast.dao.JavaBean.Student_JavaBean.stu_vo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateStudent")
public class stu_update_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //学生信息修改
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String id=request.getParameter("stuId");
        String name=request.getParameter("stuName");
        String pass=request.getParameter("stuPassword");
        stu_vo stu = new stu_vo(id,name,pass);
        stu_dao dao = new stu_dao();
        PrintWriter out=response.getWriter();
        List<stu_vo> stuList = new ArrayList<stu_vo>();

        try {
            stuList = dao.findStudentById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //修改前判断学生是否存在
        if(stuList.size()==0){
            out.print("<script language='javascript'>alert('修改失败！无此学生。');window.location.href='SelectAllStudent';</script>");
        }else{
            try {
                dao.update(stu);
                out.print("<script language='javascript'>alert('修改成功！');window.location.href='SelectAllStudent';</script>");
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
