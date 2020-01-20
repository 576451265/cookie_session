<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/01/20
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    int i = 5;
    System.out.println("java");
    String contextPath = request.getContextPath();
    out.print(contextPath);//定义在哪在哪输出
  %>
  <%!
    int i = 3;//定义成员 少用
  %>
<%-- i的值会被输出到页面上5 --%>
  <%= i %>
  <br>
  <a href="/cookie&session/cookieDemo1">创建一个cookie</a><br>
  <a href="/cookie&session/cookieTest">cookie小案例</a><br>
  <a href="home.jsp">cookie小案例去home.jsp</a><br>

  <% response.getWriter().write("response..."); //会先于out输出%>

  </body>
</html>
