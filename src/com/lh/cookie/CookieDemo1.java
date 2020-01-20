package com.lh.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: cookie 快速入门
 * @author LuoH
 * @date 2020/01/20
 */
@WebServlet("/cookieDemo1")
public class CookieDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * 在tomcat 8 之前 cookie中不能直接存储中文数据。
         * 需要将中文数据转码---一般采用URL编码(%E3)
         * 在tomcat 8 之后，cookie支持中文数据。特殊字符还是不支持，建议使用URL编码存储，URL解码解析
         */
        //1.创建对象
        Cookie c = new Cookie("msg", "hello");
        Cookie c1 = new Cookie("msg2", "你好");

        /*持久化存储：
        setMaxAge(int seconds)
			1. 正数：将Cookie数据写到硬盘的文件中。持久化存储。并指定cookie存活时间，时间到后，cookie文件自动失效
			2. 负数：默认值 浏览器一关就没了
			3. 零：删除cookie信息
         */
        //2.设置cookie存活时间
        c.setMaxAge(300);//cookie持久化到硬盘 300秒后自动删除文件

        /*
        cookie共享问题？
			1. 假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中cookie能不能共享？
				* 默认情况下cookie不能共享

				* setPath(String path):设置cookie的获取范围。默认情况下，设置当前的虚拟目录
					* 如果要共享，则可以将path设置为"/"
		    2. 不同的tomcat服务器间cookie共享问题？
				* setDomain(String path):如果设置一级域名相同，那么多个服务器之间cookie可以共享
					* setDomain(".baidu.com"),那么tieba.baidu.com和news.baidu.com中cookie可以共享

         */
        //设置path,让当前服务器下部署的所有项目共享cookie信息
        c.setPath("/");
        c1.setPath("/");

        //3.发送cookie
        resp.addCookie(c);
        resp.addCookie(c1);



        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write("<a href = '/cookie&session/cookieDemo2'>跳转到cookieDemo2</a>");
        /*
        Cookie的特点和作用
        1. cookie存储数据在客户端浏览器
        2. 浏览器对于单个cookie 的大小有限制(4kb) 以及 对同一个域名下的总cookie数量也有限制(20个)

		* 作用：
        1. cookie一般用于存出少量的不太敏感的数据
        2. 在不登录的情况下，完成服务器对客户端的身份识别
         */

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
