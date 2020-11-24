
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.itcast.domain.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
    function deleteBook(id) {

    if(confirm("您确定删除么")){
    location.href="${pageContext.request.contextPath}/delBookServlet?id="+id;
    }
    else{
    location.href="${pageContext.request.contextPath}/bookListServlet"
    }

    }
    </script>

    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">图书库存</h3>
    <div  style="float: left;margin:5px"  >
        <form action="${pageContext.request.contextPath}/findBookServlet" method="post" class="form-inline">
            <div class="form-group">
                <label for="bookClassify">查询条件：</label>
                <select  id="bookClassify" name="bookFind" class="form-control" >
                    <option value="图书编号" name="bookFind"  selected>图书编号</option>
                    <option value="书名" name="bookFind" >书名</option>
                    <option value="图书类别"  name="bookFind" >图书类别</option>
                </select>

                <div class="form-group">
                    <input type="text" class="form-control" id="book" name="book" placeholder="请输入查询信息">
                </div>
            </div>
            <button type="submit" class="btn btn-default">查询图书</button>
        </form>
    </div>

    <div style="float: right;margin:5px">
        <a class="btn btn-primary" href="book_add.jsp">添加图书</a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/bookEditListServlet">查看图书操作记录</a>
        <a><input type="Button" value="返回" class="btn btn-default" onclick="window.history.back()"></a>
    </div>

    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th> <input type="checkbox" id ="firstCb"></th>
            <th>编号</th>
            <th>id</th>r
            <th>书名</th>
            <th>类别</th>
            <th>数量</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${books}" var="book" varStatus="s">
            <tr>
                <td> <input type="checkbox" name="uid" value="${book.bookId}"></td>
                <td>${s.count}</td>
                <td>${book.bookId}</td>
                <td>${book.bookName}</td>
                <td>${book.bookClassify}</td>
                <td>${book.bookCount}</td>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findBookByIdServlet?id=${book.bookId}">修改</a>
                    <a class="btn btn-default btn-sm" href="javascript:deleteBook('${book.bookId}');">删除</a></td>
            </tr>
        </c:forEach>
    </tr>

    </table>
</div>
<div  role="alert" style="text-align: center">
    <button type="button" class="close" data-dismiss="alert" >
        <span>&times;</span>
    </button>
    <strong>${login_msg}</strong>
</div>
</body>
</html>
