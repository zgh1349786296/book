<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script language="JavaScript">
        function jump() {
            if(window.confirm("是否返回登录界面")) {
                window.history.back();
            }
        }
        function validate(form) {
            if(form.bookId.value==""){
                alert("图书编号不能为空");
                return  false;
            }
            else if(form.bookName.value==""){
                alert("书名不能为空");
                return  false;
            }
            else if(form.bookCount.value==""){
                alert("图书数量不能为空");
                return  false;
            }
            else{
                return  true;
            }
        }
    </script>

    <title>添加图书</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>注册</h3></center>
    <form action="${pageContext.request.contextPath}/addBookServlet" method="post " onsubmit="return validate(this);">
        <div class="form-group">
            <label for="bookId">图书编号：</label>
            <input type="text" class="form-control" id="bookId" name="bookId" placeholder="请输入图书编号">
        </div>

        <div class="form-group">
            <label for="bookName">书名：</label>
            <input type="text" class="form-control" id="bookName" name="bookName" placeholder="请输入书名">
        </div>

        <div class="form-group">
            <label for="bookClassify">图书类别：</label>
            <select  id="bookClassify" name="bookClassify" class="form-control" >
                <option value="学习" name="bookClassify"  selected>学习</option>
                <option value="生活" name="bookClassify" >生活</option>
                <option value="娱乐"  name="bookClassify" >娱乐</option>
            </select>
        </div>

        <div class="form-group">
            <label for="bookCount">图书数量：</label>
            <input type="number" class="form-control" id="bookCount" name="bookCount" placeholder="请输入图书数量">
        </div>

        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" >
                <span>&times;</span>
            </button>
            <strong>${add_msg}</strong>
        </div>


        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" onclick="jump()" value="返回" />
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