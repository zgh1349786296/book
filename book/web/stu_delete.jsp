<%@ page import="cn.itcast.dao.JavaBean.Student_JavaBean.stu_vo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13789
  Date: 2020/11/17
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除学生</title>
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
<div class="container">
<%
    List<stu_vo> stuList = (List<stu_vo>) (request.getAttribute("stuList"));
%>
<div style="text-align: center;">
    <h3>请再次确认要删除的学生，若并非要删除的学生，请点击<a href="SelectAllStudent" methods="post">这里</a>回到总页面</h3>
    <br>
    <h3><span style="color: red; ">注意：删除学生时会同时删除该学生的归还记录！！！</span></h3>
</div>
    <br>
    <br>
    <br>
    <br>
    <div style="text-align: center;">
<form action="DeleteStudent" method="post" class="form-inline">
    <table border="1" class="table table-bordered table-hover">
        <%
            for(int i=0;i<stuList.size();i++){
                stu_vo stu = stuList.get(i);
        %>
        <tr><td class="success">学生学号：</td><td><input type="text" name="stuId" value="<%=stu.getStuId()%>" readonly size="60%"></td></tr>
        <tr><td class="success">学生姓名：</td><td><input type="text" name="stuName" value="<%=stu.getStuName()%>" readonly size="60%"></td></tr>
        <%
            }
        %>
    </table>
    <input type="submit" value="删除" class="btn btn-default">
</form>
    </div>
</div>
</body>
</html>
