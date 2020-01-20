<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/01/20
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //1.获取所有的cookie
    Cookie[] cookies = request.getCookies();
    boolean flag = false;
    //2.遍历cookie数组
    for (Cookie cookie : cookies) {
        //3.获取所有的cookie名称
        String name = cookie.getName();
        //4.判断名称是否为：lastTime
        if ("lastTime".equals(name)) {
            flag = true;
            //有 不是第一次访问
            //设置cookie的value
            //获取当前时间的字符串，重新设置cookie的值，重新发送
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);
            System.out.println("编码前： " + str_date);
            //URL编码
            str_date = URLEncoder.encode(str_date, "utf-8");
            System.out.println("编码后： " + str_date);

            cookie.setValue(str_date);
            //设置cookie的存活时间
            //cookie.setMaxAge(60 * 60 * 24 * 30);//一个月的秒
            response.addCookie(cookie);

            //响应数据
            String value = cookie.getValue();
            //URL解码
            System.out.println("解码前： " + value);
            value = URLDecoder.decode(value, "utf-8");
            System.out.println("解码后： " + value);
            out.write("<h1>欢迎回来，您上次访问时间为：" + value + "</h1>");
        }
        break;
    }
    if (cookies == null || cookies.length == 0 || flag == false) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str_date = sdf.format(date);
        System.out.println("编码前： " + str_date);
        //URL编码
        str_date = URLEncoder.encode(str_date, "utf-8");
        System.out.println("编码后： " + str_date);
        Cookie cookie = new Cookie("lastTime", str_date);
        //设置cookie的存活时间
        //cookie.setMaxAge(60 * 60 * 24 * 30);//一个月的秒
        response.addCookie(cookie);
%>
        <h1>您好 欢迎首次访问</h1>

<%
        }
%>

</body>
</html>
