<%@ page import="cn.itcast.dao.JavaBean.Books_JavaBean.books_vo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13789
  Date: 2020/11/17
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借出图书</title>
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
        function isbookOut() {
            if(confirm("是否借阅？")){
                document.bookOut.submit();
            }
        }
    </script>
</head>
<body>
<%
    List<books_vo> bookList = (List<books_vo>) (request.getAttribute("bookList"));
%>
<div class="container">
    <br>
    <br>
    <br>
    <br>
    <div style="text-align: center;">
<form action="BooksOut" method="post" class="form-inline" name="bookOut">
    <table class="table table-bordered table-hover">
        <%
            for(int i=0;i<bookList.size();i++){
                books_vo book = bookList.get(i);
        %>
        <tr><td class="success">学生学号：</td><td><input type="text" name="stuId" required size="60%"></td></tr>
        <tr><td class="success">图书编号：</td><td><input type="text" name="bookId" value="<%=book.getBookId()%>" readonly size="60%"></td></tr>
        <tr><td class="success">图书名称：</td><td><input type="text" name="bookName" value="<%=book.getBookName()%>" readonly size="60%"></td></tr>
        <tr><td class="success">图书类型：</td><td><input type="text" name="bookClassify" value="<%=book.getBookClassify()%>" readonly size="60%"></td></tr>
        <tr><td class="success">图书数量：</td><td><input type="text" name="bookCount" value="<%=book.getBookCount()%>" readonly size="60%"></td></tr>
        <%
            }
        %>
    </table>
    <input type="button" value="借阅" class="btn btn-default" onclick="isbookOut()">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="重置" class="btn btn-default">
</form>
    </div>
</div>
</body>
</html>
