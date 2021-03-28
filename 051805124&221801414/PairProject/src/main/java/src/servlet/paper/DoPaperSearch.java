package src.servlet.paper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.PaperBean;
import src.service.PaperDao;

/**
 * Servlet implementation class DoPaperSearch
 */
@WebServlet("/dopapersearch")
public class DoPaperSearch extends HttpServlet {

	static String curContent = "";
	static String curOption = "";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//当前页
		int curPage = 1;
		ArrayList<PaperBean> list = new ArrayList<PaperBean>();
		
		String requestPage = request.getParameter("cp");
		if(requestPage != null) {
			curPage = Integer.parseInt(requestPage);
		}
		String option = request.getParameter("plugin");
		String content = request.getParameter("searchContent");
		if(content == null || content.equals("")) {
			content = curContent;
		}else {
			curContent = content;
		}
		if(option == null || option.equals("")) {
			option = curOption;
		}else {
			curOption = option;
		}
		list = PaperDao.searchPaper(content,option,curPage);
		for(int i=0;i < list.size();i++) {
			list.get(i).setKeyword(PaperDao.getKeyWord(list.get(i).getPaperNum()));
		}
		
		int itemNum = PaperDao.getItemNum(content, option);
		int totalPage = itemNum/5;
		if(itemNum%5 != 0)
			totalPage++;
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalNum", itemNum);
		request.setAttribute("curContent", curContent);
		request.setAttribute("paperlist", list);
		System.out.print(list.size());
		request.getRequestDispatcher("index_two_search.jsp").forward(request, response);
	}

}
