<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.itcast.dao.DAO.*" %>
<%@ page import="cn.itcast.domain.*" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.itcast.service.impl.UserServiceImpl" %>


<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <title>还书</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function bookin(stuId, bookId, time) {
            if(confirm("您确定归还这本书么？"))
                location.href="bookInServlet?stuId="+stuId+"&bookId="+bookId+"&time="+time;
        }
    </script>

</head>
<body>
<%
    try{
        Student student = null;
        Cookie[] cookies = request.getCookies();
        String sid = "";
        UserServiceImpl usi = new UserServiceImpl();
        sid = usi.CookieStu(cookies);
        //if(cookies != null) {
            /*for (Cookie c : cookies) {
                if (c.getName().equalsIgnoreCase("stuId")) {
                    sid = c.getValue();
                }
            }*/
        if(sid==null){
            response.sendRedirect("please_login.jsp");
        }
        else{
            StudentDAO DAO = new StudentDAO();

            try {
                student = DAO.searchStuInf(sid);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("stu_fail.jsp");
            }

        BookRecorderDAO InDAO = new BookRecorderDAO();
        BookRecorderDAO OutDAO = new BookRecorderDAO();
        List<BookRecord> bookRecordInList = InDAO.bookInAlready(student.getStuId());
        List<BookRecord> bookRecordOutList = OutDAO.bookOut(student.getStuId());
%>
<div class="container">
    <h3 style="text-align: center">未归还图书</h3>
    <div style="float: right;margin:5px">
        <a class="btn btn-primary" href="stu_Inf.jsp" id="stuInf">个人信息</a>
    </div>
    <div style="float: right;margin:5px">
        <a class="btn btn-primary" href="stu_inOutRecord.jsp" id="bookRecord">借阅记录</a>
    </div>
    <div style="float: right;margin:5px">
        <a class="btn btn-primary" href="stu_booklist.jsp" id="bookout">借书</a>
    </div>

    <form  id="formout" action=" " method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>书名</th>
                <th>归还时间</th>
                <th>操作</th>
            </tr>
            <%
                for (BookRecord BookRecord : bookRecordOutList) {
                    Book book = new Book();
                    BookDAO bookDAO = new BookDAO();
                    book = bookDAO.searchInf(BookRecord.getBookId());
            %>
            <tr>
                <td><%=BookRecord.getBookId()%></td>
                <td><%=book.getBookName()%></td>
                <td><%=BookRecord.getTime()%></td>
                <td><a class="btn btn-primary" href="javascript:bookin('<%=student.getStuId()%>','<%=book.getBookId()%>','<%=BookRecord.getTime()%>');">还书</a></td>
            </tr>
<%
    }
%>
        </table>
    </form>
</div>
<%

        }
    }
    catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("stu_fail.jsp");
    }


%>
</body>
</html>
