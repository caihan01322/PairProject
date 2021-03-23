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
 * Servlet implementation class DoUserAdd
 */
@WebServlet("/douseradd")
public class DoUserAdd extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userName = request.getParameter("username");
		String psw = request.getParameter("pwd");
		String repsw = request.getParameter("repwd");
		if(userName.equals("") || psw.equals("") || repsw.equals("")) {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('请输入账号密码');");
			out.write("window.location.href='register.jsp';");
			out.write("</script>");
			return;
		}
		if(!psw.equals(repsw)) {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('两次密码不一致');");
			out.write("window.location.href='register.jsp';");
			out.write("</script>");
			return;
		}
		User user = new User(userName,psw);
		
		//输入到数据库
		int count = UserDao.insert(user);
		
		if(count > 0) {
			response.sendRedirect("index.jsp");
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('用户注册失败');");
			out.write("window.location.href='register.jsp';");
			out.write("</script>");
		}
	}

}
