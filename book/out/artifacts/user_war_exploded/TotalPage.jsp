<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.itcast.service.impl.UserServiceImpl" %>
<html>
<head>
  <title>图书管理系统——管理员版</title>
  <meta charset="utf-8">
  <link type="text/css" rel="stylesheet" href="css/style.css"/>
  <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="js/menu.js"></script>

</head>

<body>
<%
  try {
    Cookie[] cookies = request.getCookies();
    String manId = null;
    UserServiceImpl usi = new UserServiceImpl();
    manId = usi.CookieMan(cookies);
    if(manId==null||manId.equals("")){
      response.sendRedirect("please_login.jsp");
    }
    else{
      try{

%>

<div class="top"></div>
<div id="header">
  <div class="logo">图书管理系统</div>
  <div class="navigation">
    <ul>
      <li><h3>欢迎您！${man.getManId()}</h3></li>
      <li><a href="ManQuitServlet"><h3>退出登录</h3></a></li>
    </ul>
  </div>
</div>

<div id="content">
  <div class="left_menu">
    <ul id="nav_dot">
      <li>
        <h4 class="M7"><span></span>学生信息管理</h4>
        <div class="list-item none">
          <a href='SelectAllStudent'  target="iframe">信息总览</a>
          <a href='stu_add.jsp' target="iframe">新增学生</a>
        </div>
      </li>
      <li>
        <h4  class="M2"><span></span>图书</h4>
        <div class="list-item none">
          <a href='book_add.jsp' target="iframe">新增图书</a>
          <a href='${pageContext.request.contextPath}/bookListServlet' target="iframe">图书管理</a>
          <a href='${pageContext.request.contextPath}/bookEditListServlet' target="iframe">查看图书操作记录</a>
        </div>
      </li>
      <li>
        <h4 class="M9"><span></span>借阅服务</h4>
        <div class="list-item none">
          <a href='SelectAllBooks' target="iframe" methods="post">借阅图书</a>
          <a href='BooksOutSelectAll' target="iframe" methods="post">借阅记录</a>
          <a href='BooksInSelectAll' target="iframe" methods="post">归还记录</a>
        </div>
      </li>
      <li>
        <h4 class="M9"><span></span>操作说明</h4>
        <div class="list-item none">
          <a href='OperationManual.jsp' target="iframe" methods="post">阅读操作手册</a>
        </div>
      </li>
    </ul>
  </div>

  <div class="m-right">
    <div class="main">
      <iframe src="OperationManual.jsp" name="iframe" style="width: 100%;height: 99%" frameborder="0" scrolling="auto"></iframe>
    </div>
  </div>

</div>

<div class="bottom"></div>
<div id="footer"><p>版权所有：吴彩元、张广浩、张京宇、武文斌</p></div>
<script>navList(12);</script>
<%
  } catch (Exception e) {
    e.printStackTrace();
    response.sendRedirect("book_fail.jsp");
  }
  }
  }
  catch (Exception e) {
    e.printStackTrace();
    response.sendRedirect("book_fail.jsp");
  }

%>
</body>
</html>
