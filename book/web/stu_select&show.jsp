
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.itcast.domain.*" %>
<%@ page import="cn.itcast.dao.JavaBean.Student_JavaBean.stu_vo" %>
<%@ page import="java.util.List" %>
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
    <%
        request.setCharacterEncoding("utf-8");
        List<stu_vo> stuList = (List<stu_vo>) (request.getAttribute("stuList"));
    %>
</head>
<body>
<div class="container">
    <br>
    <div style="text-align: center;">
        <form action="SelectStudent" method="post" class="form-inline">
            <div class="form-group">
                <table border="1" class="table table-bordered table-hover">
                    <tr>
                        <td>
                            <select name="select_condition">
                                <option>学生学号</option>
                                <option>学生姓名</option>
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
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <td>学生学号</td>
            <td>学生姓名</td>
            <td>操作</td>
        </tr>
        <%
            for (int i = 0; i < stuList.size(); i++) {
                stu_vo stu = stuList.get(i);
        %>
        <tr>
            <td><%=stu.getStuId()%></td>
            <td><%=stu.getStuName()%></td>
            <td align="right">
                <a class="btn btn-primary" href="SelectStudent?action=update&stuId=<%=stu.getStuId()%>">修改</a>
                <a class="btn btn-primary" href="SelectStudent?action=delete&stuId=<%=stu.getStuId()%>">删除</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
