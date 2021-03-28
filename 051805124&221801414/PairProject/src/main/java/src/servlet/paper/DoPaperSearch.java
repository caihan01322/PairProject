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
	static ArrayList<PaperBean> list = new ArrayList<PaperBean>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//当前页
		int curPage = 1;
		//每页显示
		int count = 5;
		String type = request.getParameter("type");
		if(type == "1") {
			list = PaperDao.showAll();
			for(int i=0;i < list.size();i++) {
				list.get(i).setKeyword(PaperDao.getKeyWord(list.get(i).getPaperNum()));
			}
		}
		else {
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
				list = PaperDao.searchPaper(content,option,curPage);
				for(int i=0;i < list.size();i++) {
					list.get(i).setKeyword(PaperDao.getKeyWord(list.get(i).getPaperNum()));
				}
			}
		}
		
		
		request.setAttribute("totalPage", list.size()%count==0?list.size()/5:list.size()/5+1);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalNum", list.size());
		request.setAttribute("curContent", curContent);
		int from = (curPage-1)*5 > 0?(curPage-1)*5:0;
		int to = curPage*5 > list.size()?list.size():curPage*5;
		ArrayList<PaperBean> t = new ArrayList<PaperBean>();
		t.addAll(list.subList(from,to));
		request.setAttribute("paperlist", t);
		request.getRequestDispatcher("index_two_search.jsp").forward(request, response);
	}

}
