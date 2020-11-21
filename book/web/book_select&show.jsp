<%@ page import="cn.itcast.dao.JavaBean.Student_JavaBean.stu_vo" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.itcast.dao.JavaBean.Books_JavaBean.books_vo" %><%--
  Created by IntelliJ IDEA.
  User: 13789
  Date: 2020/11/17
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查找图书</title>
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
<div class="container">
    <br>
    <div style="text-align: center;">
<form action="SelectBooks" method="post" class="form-inline">
    <div class="form-group">
    <table border="1" class="table table-bordered table-hover">
        <tr>
            <td>
                <select name="select_condition">
                    <option>图书编号</option>
                    <option>图书名称</option>
                    <option>图书类型</option>
                </select>
            </td>
            <td><input type="text" name="find"></td>
            <td><input type="submit" value="搜索"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
    </div>
</form>
    </div>

<%
    List<books_vo> bookList = (List<books_vo>) (request.getAttribute("bookList"));
%>

<table border="1" class="table table-bordered table-hover">
    <tr class="success">
        <td>图书编号</td>
        <td>图书名称</td>
        <td>图书类型</td>
        <td>图书数量</td>
        <td>操作</td>
    </tr>
    <%
        for (int i = 0; i < bookList.size(); i++) {
            books_vo book = bookList.get(i);
    %>
    <tr>
        <td><%=book.getBookId()%></td>
        <td><%=book.getBookName()%></td>
        <td><%=book.getBookClassify()%></td>
        <td><%=book.getBookCount()%></td>
        <td align="right"><a href="SelectBooks?action=Out&bookId=<%=book.getBookId()%>">借阅</a></td>
    </tr>
    <%
        }
    %>
</table>
</div>
</body>
</html>
