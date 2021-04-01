package src.servlet.paper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.PaperBean;
import src.service.PaperDao;

/**
 * Servlet implementation class DoPaperEdit
 */
@WebServlet("/DoPaperEdit")
public class DoPaperEdit extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String curpage = request.getParameter("curPage");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String magazine = request.getParameter("magazine");
		String link = request.getParameter("link");
		String abst = request.getParameter("abst");
		int flag = PaperDao.updatePaper(id, title, year, magazine, link, abst);
		
		if(flag>0) {
			request.setAttribute("id", id);
			request.setAttribute("curpage", curpage);
			request.setAttribute("title", title);
			request.setAttribute("year", year);
			request.setAttribute("magazine", magazine);
			request.setAttribute("link", link);
			request.setAttribute("abst", abst);
			request.setAttribute("keyword", PaperDao.getKeyWord(id));
			request.getRequestDispatcher("index_two_edit.jsp").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('修改失败');");
			out.write("window.location.href='javascript:history.go(-1);'");
			out.write("</script>");
		}
	}

}
