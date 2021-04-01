package src.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.User;
import src.service.UserDao;

/**
 * Servlet implementation class DoUserLogin
 */
@WebServlet("/douserlogin")
public class DoUserLogin extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userName = request.getParameter("username");
		String psw = request.getParameter("pwd");
		if(userName.equals("") || psw.equals("")) {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('请输入账号密码');");
			out.write("window.location.href='login.jsp';");
			out.write("</script>");
			return;
		}
		
		User user = new User(userName,psw);
		
		//输入到数据库
		boolean f = UserDao.isUser(user);
		
		if(f) {
			response.sendRedirect("index.jsp");
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('账号或密码错误');");
			out.write("window.location.href='login.jsp';");
			out.write("</script>");
		}
	}

}
