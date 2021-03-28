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
@WebServlet("/DoSearchSelect")
public class DoSearchSelect extends HttpServlet {
	
	ArrayList<Paper> list = new ArrayList<>();
	List<Paper> subList = new ArrayList<>();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1;
		String cp = request.getParameter("cp");
		if(cp != null) {
			cpage = Integer.parseInt(cp);
		}
		String condition = (String) request.getParameter("condition");
		if(!condition.equals(null) && !condition.equals("")) {
			list = ArticleDao.selectSpecial(condition);
		}
		if(list.size()>cpage*12) {
			subList.addAll(list.subList((cpage-1)*12, cpage*12));
		}
		else  {
			if(list.size() != 0)
			subList.addAll(list.subList((cpage-1)*12, list.size()));
		}
		request.setAttribute("paperList", subList);
		request.setAttribute("tsum", list.size());
		request.setAttribute("tpage", list.size()/12+1);
		request.setAttribute("cpage", cpage);
		request.getRequestDispatcher("Search.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cpage = 1;
		String cp = request.getParameter("cp");
		if(cp != null) {
			cpage = Integer.parseInt(cp);
		}
		subList.clear();
		if(list.size() > cpage*12) {
			subList.addAll(list.subList((cpage-1)*12, cpage*12));
		}
		else {
			if(list.size()!=0)
			subList.addAll(list.subList((cpage-1)*12, list.size()));
		}
		request.setAttribute("paperList", subList);
		request.setAttribute("tsum", list.size());
		request.setAttribute("tpage", list.size()/12+1);
		request.setAttribute("cpage", cpage);
		request.getRequestDispatcher("Search.jsp").forward(request, response);
	}

}