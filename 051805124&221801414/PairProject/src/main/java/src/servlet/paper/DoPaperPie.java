package src.servlet.paper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;

import src.service.PaperDao;

/**
 * Servlet implementation class DoPaperPie
 */
@WebServlet(urlPatterns = "/DoPaperPie")
public class DoPaperPie extends HttpServlet {
	
	private static final int MAX_NUM = 10;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		Map<String, Integer> map = new HashMap<String,Integer>();
		String[] keywords = new String[MAX_NUM];
		int[] occur = new int[MAX_NUM];
		int i = 0;
		
		ArrayList<String> list = PaperDao.getKeyWords();
			for(String str : list) {
			if (map.containsKey(str)) {
    			int occurs = map.get(str);
    			map.put(str, occurs+1);
    		} else {
    			map.put(str, 1);
    		}
		}
		map = map.entrySet().stream()
	    		.sorted(Map.Entry.<String, Integer>comparingByValue()
	            .reversed().thenComparing(Map.Entry.comparingByKey())).limit(MAX_NUM)                      
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
	            		, (e1, e2) -> e1, LinkedHashMap::new));
		ArrayList<HashMap.Entry<String, Integer>> Top10 = new ArrayList <HashMap.Entry <String, Integer> > (map.entrySet());
		for(HashMap.Entry<String, Integer> Map : Top10) {
            keywords[i] = Map.getKey();
            occur[i++] = Map.getValue();
        }
		
		request.setAttribute("keyword", keywords);
		request.setAttribute("occur", occur);
		request.getRequestDispatcher("index_three_chart.jsp").forward(request, response);
	}
		
		
}

