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
@WebServlet("/DoUserSelect")
public class DoUserSelect extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1;
		int count = 12;
		int totalPage;
		int total;
		String cp = request.getParameter("cp");
		if(cp != null) {
			cpage = Integer.parseInt(cp);
		}
		ArrayList<Paper> list = ArticleDao.selectAll(cpage,count);
		total = ArticleDao.total();
		if(total % 12 == 0) {
			totalPage = total/12;
		}
		else totalPage = total/12+1;
		request.setAttribute("paperList", list);
		request.setAttribute("tsum", total);
		request.setAttribute("tpage", totalPage);
		request.setAttribute("cpage", cpage);
		request.getRequestDispatcher("Head.jsp").forward(request, response);
	}

}