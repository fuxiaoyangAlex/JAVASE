package org.lanqiao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.lanqiao.dao.LoginDao;
import org.lanqiao.entity.Login;

//控制器层：接受view请求 并分发给Model处理
@WebServlet("/org.lanqiao.login/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//处理登陆请求 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//接受前台传来的参数
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		System.out.print(name);
		Login login = new Login(name,pwd);//用户名和密码
		//调用模型层的登陆功能
		int result = LoginDao.login(login);
			if(result>0) {//成功
				response.sendRedirect("welcome.jsp");
			}else {
				response.sendRedirect("fail.jsp");
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
