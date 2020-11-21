<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">

        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改图书信息</h3>
        <form action="${pageContext.request.contextPath}/updateBookServlet" method="post" >

            <div class="form-group">
                <label for="bookId">图书编号：</label>
                <input type="text" class="form-control" id="bookId" name="bookId" value="${book.bookId}" readonly="readonly">
            </div>

            <div class="form-group">
                <label for="bookName">书名：</label>
                <input type="text" class="form-control" id="bookName" name="bookName" value="${book.bookName}" >
            </div>

            <div class="form-group">
                <label for="bookClassify">图书类别：</label>
                <select  id="bookClassify" name="bookClassify" class="form-control"  value="${book.bookClassify}" >
                    <option value="学习" name="bookClassify"  selected>学习</option>
                    <option value="生活" name="bookClassify" >生活</option>
                    <option value="娱乐"  name="bookClassify" >娱乐</option>
                </select>
            </div>

            <div class="form-group">
                <label for="bookCount">图书数量：</label>
                <input type="number" class="form-control" id="bookCount" name="bookCount" value="${book.bookCount}" >
            </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" value="返回 " onclick="jump()" />
             </div>
        </form>
            <script>
                function jump(){
                    window.history.back();
                }
            </script>
        </div>
    </body>
</html>