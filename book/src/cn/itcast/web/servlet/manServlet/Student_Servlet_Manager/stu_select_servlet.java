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

@WebServlet("/SelectStudent")
public class stu_select_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //学生搜索
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String operation=request.getParameter("action");
        List<stu_vo> stuList = new ArrayList<stu_vo>();
        stu_dao dao = new stu_dao();
        PrintWriter out=response.getWriter();

        //根据选择的方式查询
        if(operation==null){
            String findInf=request.getParameter("find");//查询的值（学号/姓名)
            String condition=request.getParameter("select_condition");
            try {
                if(condition.equals("学生学号")){
                    stuList = dao.findStudentById(findInf);
                }else if(condition.equals("学生姓名")){
                    stuList = dao.findStudentByName(findInf);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //搜索结果提示
            if(stuList.size()==0){
                out.print("<script language='javascript'>alert('没有该学生！');window.location.href='SelectAllStudent';</script>");
            }else if(stuList.size()!=0){
                request.setCharacterEncoding("utf-8");
                request.setAttribute("stuList",stuList);
                request.getRequestDispatcher("/stu_select&show.jsp").forward(request,response);
            }
        }

        if(operation!=null){
            String id = request.getParameter("stuId");
            try {
                stuList = dao.findStudentById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(operation.equals("update")){
                request.setAttribute("stuList",stuList);
                request.getRequestDispatcher("/stu_update.jsp").forward(request,response);
            }else if(operation.equals("delete")){
                request.setAttribute("stuList",stuList);
                request.getRequestDispatcher("/stu_delete.jsp").forward(request,response);
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
