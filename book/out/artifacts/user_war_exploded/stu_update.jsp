<%@ page import="cn.itcast.dao.JavaBean.Student_JavaBean.stu_vo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13789
  Date: 2020/11/17
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
    <script language="JavaScript" src="JavaScript/stu_update_test.js"></script>
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
    List<stu_vo> stuList = (List<stu_vo>) (request.getAttribute("stuList"));
%>
<div class="container">
    <br>
    <br>
    <br>
    <br>
    <div style="text-align: center;">
<form action="UpdateStudent" method="post" name="Student_Update" class="form-inline">
    <table border="1" class="table table-bordered table-hover">
        <%
            for(int i=0;i<stuList.size();i++){
                stu_vo stu = stuList.get(i);
        %>
        <tr><td class="success">学生学号：</td><td><input type="text" name="stuId" size="60%" value="<%=stu.getStuId()%>" readonly></td></tr>
        <tr><td class="success">学生姓名：</td><td><input type="text" name="stuName" size="60%" value="<%=stu.getStuName()%>"></td></tr>
        <tr><td class="success">学生密码：</td><td><input type="password" name="stuPassword" size="60%" value="<%=stu.getStuPassword()%>"></td></tr>
        <%
            }
        %>
    </table>
    <input type="button" value="修改" class="btn btn-default" onclick="stu_update_test()">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="重置" class="btn btn-default">
</form>
    </div>
</div>
</body>
</html>
