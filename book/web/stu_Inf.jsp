
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.itcast.dao.DAO.StudentDAO" %>
<%@ page import="cn.itcast.domain.Student" %>
<%@ page import="java.io.IOException" %>
<%@ page import="cn.itcast.service.impl.UserServiceImpl" %>
<html>

<!--标题-->
<head>
    <title>个人信息</title>
    <meta charset="utf-8">
    <script language="JavaScript" src="test.js"></script>
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
%>
<h3  align="center">个人信息</h3>
<div class="container" style="width: 400px;">
    <form name="inf" action="UpdateServlet"><!--信息表单-->


        <div class="form-group">
            <label for="sid">学号：</label>
            <input type="text" class="form-control" id="sid" name="sid" value="<%=student.getStuId()%>"  readonly>
        </div>

        <div class="form-group">
            <label for="sname">姓名：</label>
            <input type="text" class="form-control" id="sname" name="sname" value="<%=student.getStuName()%>"  >
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" class="form-control" id="password" name="password" value="<%=student.getStuPassword()%>"  >


        </div>

        <div cellspacing="9" class="form-group" align="center">
            <tr>
                <td><input type="Button" value="修改" class="btn btn-primary" onclick=submit()></td>
                <td><input type="Button" value="返回" class="btn btn-default" onclick="window.history.back()"></td>
            </tr>
        </div>


    </form>

</div>
<%
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("stu_fail.jsp");
                }
            }
    } catch (NumberFormatException | IOException e) {
    response.sendRedirect("stu_fail.jsp");
    e.printStackTrace();
}
%>
</body>
</html>
