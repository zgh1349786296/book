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

@WebServlet("/AddStudent")
public class stu_add_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //学生增加
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String id=request.getParameter("stuId");
        String name=request.getParameter("stuName");
        String pass=request.getParameter("stuPassword");
        stu_vo stu = new stu_vo();
        stu_dao dao = new stu_dao();
        List<stu_vo> stuList = new ArrayList<stu_vo>();
        PrintWriter out=response.getWriter();

        //查询学号是否存在
        try {
            stuList = dao.findStudentById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0;i<stuList.size();i++){
            stu = stuList.get(i);
        }

        //学号不存在则可以添加，存在则提示
        if(stu.getStuId()==null){
            try {
                stu.setStuId(id);
                stu.setStuName(name);
                stu.setStuPassword(pass);
                dao.add(stu);
                out.print("<script language='javascript'>alert('添加成功');window.location.href='stu_add.jsp';</script>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(stu.getStuId()!=null){
            out.print("<script language='javascript'>alert('学号重复，请重试！');window.location.href='stu_add.jsp';</script>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
