package cn.itcast.web.servlet.manServlet.Student_Servlet_Manager;


import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_dao;
import cn.itcast.dao.JavaBean.BooksService_JavaBean.BooksService_vo;
import cn.itcast.dao.JavaBean.Student_JavaBean.stu_dao;
import cn.itcast.domain.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DeleteStudent")
public class stu_delete_servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //学生删除
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String id=request.getParameter("stuId");
        stu_dao dao = new stu_dao();
        PrintWriter out=response.getWriter();
        BooksService_dao daoSer = new BooksService_dao();
        List<BooksService_vo> booksOutList = new ArrayList<BooksService_vo>();

        //查询要删除的学生是否有借阅未还
        try {
            booksOutList = daoSer.booksOutSelectByStuId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //有则提示删除失败，没有则删除该学生
        if(booksOutList.size()!=0){
            out.print("<script language='javascript'>alert('删除失败！该学生还有未归还图书。');window.location.href='SelectAllStudent';</script>");
        }else{
            try {
                daoSer.bookInDelete(id);//删除学生的同时删除归还记录
                dao.delete(id);
                out.print("<script language='javascript'>alert('删除成功！');window.location.href='SelectAllStudent';</script>");
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
