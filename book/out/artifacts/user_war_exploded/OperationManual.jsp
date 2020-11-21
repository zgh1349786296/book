<%--
  Created by IntelliJ IDEA.
  User: 13789
  Date: 2020/11/18
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作说明</title>
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
    <div style="text-align: center;">
        <table>
            <tr><td class="success"><h2>图书管理系统操作说明</h2></td></tr>
        </table>
        <br>
     <table class="table table-bordered table-hover">
        <tr><td class="success">查看所有学生信息：</td><td>学生信息管理->信息总览</td></tr>
        <tr><td class="success">增加学生信息：</td><td>学生信息管理->新增学生</td></tr>
        <tr><td class="success">修改学生信息：</td><td>学生信息管理->信息总览->修改</td></tr>
        <tr><td class="success">删除学生信息：</td><td>学生信息管理->信息总览->删除</td></tr>
         <tr><td class="success">查看图书信息：</td><td>图书->图书信息总览->查询</td></tr>
         <tr><td class="success">增加图书信息：</td><td>图书->新增图书</td></tr>
         <tr><td class="success">修改图书信息：</td><td>图书->图书信息总览->修改</td></tr>
         <tr><td class="success">删除图书信息：</td><td>图书->图书信息总览->删除</td></tr>
         <tr><td class="success">查看图书操作记录：</td><td>图书->图书操作记录</td></tr>
        <tr><td class="success">图书外借：</td><td>借阅服务->借阅图书->借阅->输入学号->借阅</td></tr>
        <tr><td class="success">查看借阅记录：</td><td>借阅服务->借阅记录</td></tr>
        <tr><td class="success">图书归还：</td><td>借阅服务->借阅记录->归还</td></tr>
        <tr><td class="success">查看归还记录：</td><td>借阅服务->归还记录</td></tr>
    </table>
    </div>
</div>
</body>
</html>
