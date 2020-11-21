<%--
  Created by IntelliJ IDEA.
  User: 13789
  Date: 2020/11/17
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加学生信息</title>
    <script language="JavaScript" src="JavaScript/stu_add_test.js"></script>
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
    <br>
    <br>
    <br>
    <br>
    <div style="text-align: center;">
<form action="AddStudent" method="post" name="Student_Add" class="form-inline">
    <table class="table table-bordered table-hover">
        <tr><td class="success">学生学号：</td><td><input type="text" name="stuId" required size="60%"></td></tr>
        <tr><td class="success">学生密码：</td><td><input type="password" name="stuPassword" required size="60%"></td></tr>
        <tr><td class="success">学生姓名：</td><td><input type="text" name="stuName" required size="60%"></td></tr>
    </table>
    <input type="button" value="添加" onclick="stu_add_test()" class="btn btn-default">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="重置" class="btn btn-default">
</form>
    </div>
</div>
</body>
</html>
