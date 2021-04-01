package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoUserSelect
 */
@WebServlet("/DoHotWordSelect")
public class DoHotWordSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<HotWord> list = new ArrayList<>();
		String year = request.getParameter("year");
		if(year.equals(null) || year.equals("")) {
			year="2017";
		}
		list = ArticleDao.getMeetingList(year);
		request.setAttribute("dataList", list);
		request.setAttribute("year",year);
		request.getRequestDispatcher("Analysis.jsp").forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<HotWord> list = new ArrayList<>();
		String year = "2017";
		list = ArticleDao.getMeetingList(year);
		request.setAttribute("dataList", list);
		request.setAttribute("year",year);
		request.getRequestDispatcher("Analysis.jsp").forward(request, response);
	}
	

}