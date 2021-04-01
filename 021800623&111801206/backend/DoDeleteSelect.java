package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoUserSelect
 */
@WebServlet("/DoDeleteSelect")
public class DoDeleteSelect extends HttpServlet {
	ArrayList<Paper> list = new ArrayList<>();
	List<Paper> subList = new ArrayList<>();
	int total=0;
	String condition = "";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1;
		int count = 12;
		condition = (String) request.getParameter("condition");
		if(!condition.equals(null) && !condition.equals("")) {
			list = ArticleDao.selectSpecial(condition,cpage,count);
			total = ArticleDao.searchTotal(condition);
		}
		request.setAttribute("paperList", list);
		request.setAttribute("tsum", total);
		request.setAttribute("tpage", total/count+1);
		request.setAttribute("cpage", cpage);
		request.getRequestDispatcher("Delete.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1;
		int count = 12;
		String cp = request.getParameter("cp");
		if(cp != null) {
			cpage = Integer.parseInt(cp);
		}
		if(!condition.equals(null) && !condition.equals("")) {
			list = ArticleDao.selectSpecial(condition,cpage,count);
		}
		request.setAttribute("paperList", list);
		request.setAttribute("tsum", total);
		request.setAttribute("tpage", total/count+1);
		request.setAttribute("cpage", cpage);
		request.getRequestDispatcher("Delete.jsp").forward(request, response);
	}
	
	

}