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
 * Servlet implementation class DoPaperDetail
 */
@WebServlet("/dopaperdetail")
public class DoPaperDetail extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String curPage = request.getParameter("cp");
		PaperBean bean = PaperDao.showPaper(id);
		
		
		request.setAttribute("id", id);
		request.setAttribute("curpage",curPage);
		request.setAttribute("title",bean.getTitle());
		request.setAttribute("year",bean.getYear());
		request.setAttribute("magazine",bean.getMagazine());
		request.setAttribute("link",bean.getLink());
		request.setAttribute("abst",bean.getAbst());
		request.setAttribute("keyword",PaperDao.getKeyWord(bean.getPaperNum()));
		request.getRequestDispatcher("index_two_edit.jsp").forward(request, response);
	}
}
