
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


    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">图书操作记录</h3>

    <div  style="float:right;margin:5px"  method="post">
            <input class="btn btn-default"  type="button" onclick="jump()" value="返回" />
    </div>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>图书编号</th>r
            <th>管理员编号</th>
            <th>操作时间</th>
            <th>操作</th>
            <th>操作数量</th>
        </tr>
        <c:forEach items="${bookedits}" var="bookedit" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${bookedit.bookId}</td>
                <td>${bookedit.manId}</td>
                <td>${bookedit.operateTime}</td>
                <td>${bookedit.operate}</td>
                <td>${bookedit.operateCount}</td>
            </tr>
        </c:forEach>
    </table>
    <hr style="bottom: 3px">
    <h6  style="text-align: center">ps:编号为0表示进行过操作，操作对象不是单一书籍或者操作失败</h6>

    <script>
        function jump(){
            window.history.back();
        }
    </script>
</div>
</body>
</html>
