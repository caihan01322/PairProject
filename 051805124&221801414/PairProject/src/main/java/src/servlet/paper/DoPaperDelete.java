package src.servlet.paper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.service.PaperDao;

/**
 * Servlet implementation class DoPaperDelete
 */
@WebServlet("/dopaperdelete")
public class DoPaperDelete extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		int count = PaperDao.deletePaper(id);
		System.out.println(request.getParameter("content")+"1");
		if(count > 0) {
			response.sendRedirect("dopapersearch?cp=" + request.getParameter("cp") + "&searchContent=" + request.getParameter("content"));
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('删除失败');");
			out.write("window.location.href='dopapersearch?cp=" + request.getParameter("cp")+"'");
			out.write("</script>");
		}
	}
}
