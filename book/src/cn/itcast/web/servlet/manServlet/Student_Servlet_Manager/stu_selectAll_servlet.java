package cn.itcast.web.servlet.manServlet.Student_Servlet_Manager;



import cn.itcast.dao.JavaBean.Student_JavaBean.stu_dao;
import cn.itcast.dao.JavaBean.Student_JavaBean.stu_vo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SelectAllStudent")
public class stu_selectAll_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //学生信息总览
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        List<stu_vo> stuList = new ArrayList<stu_vo>();
        stu_dao dao = new stu_dao();

        try {
            stuList = dao.selectAllStuInf();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("stuList",stuList);
        request.getRequestDispatcher("/stu_select&show.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
