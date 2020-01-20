package com.lh.cookiecases;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
案例：记住上一次访问时间
		1. 需求：
			1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
			2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串

		2. 分析：
			1. 可以采用Cookie来完成
			2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
				1. 有：不是第一次访问
					1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
					2. 写回Cookie：lastTime=2018年6月10日11:50:01
				2. 没有：是第一次访问
					1. 响应数据：您好，欢迎您首次访问
					2. 写回Cookie：lastTime=2018年6月10日11:50:01
 */
@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {

    public String getStrDate() throws UnsupportedEncodingException {
        //获取当前时间的字符串，重新设置cookie的值，重新发送
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str_date = sdf.format(date);
        System.out.println("编码前： " + str_date);
        //URL编码
        str_date = URLEncoder.encode(str_date, "utf-8");
        System.out.println("编码后： " + str_date);
        return str_date;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应消息的数据格式及编码
        response.setContentType("text/html;charset=utf-8");
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
                String strDate = getStrDate();
                cookie.setValue(strDate);
                //设置cookie的存活时间
                //cookie.setMaxAge(60 * 60 * 24 * 30);//一个月的秒
                response.addCookie(cookie);

                //响应数据
                String value = cookie.getValue();
                //URL解码
                System.out.println("解码前： " + value);
                value = URLDecoder.decode(value, "utf-8");
                System.out.println("解码后： " + value);
                response.getWriter().write("<h1>欢迎回来，您上次访问时间为：" + value + "</h1>");
            }
            break;
        }
        if (cookies == null || cookies.length == 0 || flag == false) {
            String str_date = getStrDate();
            Cookie cookie = new Cookie("lastTime", str_date);
            //设置cookie的存活时间
            //cookie.setMaxAge(60 * 60 * 24 * 30);//一个月的秒
            response.addCookie(cookie);
            response.getWriter().write("<h1>您好，欢迎首次访问</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
