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
@WebServlet("/DoECCVSelect")
public class DoECCVSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<HotWord> list = new ArrayList<>();
		if(list != null) {
			list = ArticleDao.getECCVChart();
		}
		
		request.setAttribute("dataList", list);
		request.setAttribute("title","ECCV");
		request.getRequestDispatcher("Analysis.jsp").forward(request, response);
	}

}