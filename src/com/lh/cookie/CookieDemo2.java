package com.lh.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: cookie 快速入门 获取cookie
 * @author LuoH
 * @date 2020/01/20
 */
@WebServlet("/cookieDemo2")
public class CookieDemo2 extends HttpServlet {
    /*
    只能在同一浏览器中访问 不能跨浏览器
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //3.获取cookie
        Cookie[] cs = req.getCookies();
        //获取数据 遍历cookies
        if (cs != null) {
            for (Cookie c : cs) {
                String name = c.getName();
                String value = c.getValue();
                System.out.println(name + ":" + value);

                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write(name + ":" + value + "</br>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
