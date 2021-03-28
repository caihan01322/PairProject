package src.servlet.paper;

import java.io.IOException;
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

import src.service.PaperDao;

/**
 * Servlet implementation class DoPaperLine
 */
@WebServlet("/DoPaperLine")
public class DoPaperLine extends HttpServlet {
	
	private static final int KEYWORD_MAX_NUM = 10;
	private static final int MAGAZINE_MAX_NUM = 3;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		Map<String, Integer> map = new HashMap<String,Integer>();
		String[] keywords = new String[KEYWORD_MAX_NUM];
		int[] occur = new int[KEYWORD_MAX_NUM];
		int i = 0;
		
		String year = request.getParameter("year");
		ArrayList<String> list = PaperDao.getKeyWordsByYear(year);
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
	            .reversed().thenComparing(Map.Entry.comparingByKey())).limit(KEYWORD_MAX_NUM)                      
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
	            		, (e1, e2) -> e1, LinkedHashMap::new));
		ArrayList<HashMap.Entry<String, Integer>> Top10 = new ArrayList <HashMap.Entry <String, Integer> > (map.entrySet());
		for(HashMap.Entry<String, Integer> Map : Top10) {
            keywords[i] = Map.getKey();
            occur[i++] = Map.getValue();
        }
		
		ArrayList<String> magazineList1 = PaperDao.getMagazineByYK(year, keywords[0]);
		ArrayList<String> magazineList2 = PaperDao.getMagazineByYK(year, keywords[1]);
		ArrayList<String> magazineList3 = PaperDao.getMagazineByYK(year, keywords[2]);
		ArrayList<String> magazineList4 = PaperDao.getMagazineByYK(year, keywords[3]);
		ArrayList<String> magazineList5 = PaperDao.getMagazineByYK(year, keywords[4]);
		ArrayList<String> magazineList6 = PaperDao.getMagazineByYK(year, keywords[5]);
		ArrayList<String> magazineList7 = PaperDao.getMagazineByYK(year, keywords[6]);
		ArrayList<String> magazineList8 = PaperDao.getMagazineByYK(year, keywords[7]);
		ArrayList<String> magazineList9 = PaperDao.getMagazineByYK(year, keywords[8]);
		ArrayList<String> magazineList10 = PaperDao.getMagazineByYK(year, keywords[9]);
		
		Map<String, Integer> map1 = PaperDao.getMagazineOccur(magazineList1);
		Map<String, Integer> map2 = PaperDao.getMagazineOccur(magazineList2);
		Map<String, Integer> map3 = PaperDao.getMagazineOccur(magazineList3);
		Map<String, Integer> map4 = PaperDao.getMagazineOccur(magazineList4);
		Map<String, Integer> map5 = PaperDao.getMagazineOccur(magazineList5);
		Map<String, Integer> map6 = PaperDao.getMagazineOccur(magazineList6);
		Map<String, Integer> map7 = PaperDao.getMagazineOccur(magazineList7);
		Map<String, Integer> map8 = PaperDao.getMagazineOccur(magazineList8);
		Map<String, Integer> map9 = PaperDao.getMagazineOccur(magazineList9);
		Map<String, Integer> map10 = PaperDao.getMagazineOccur(magazineList10);
		
		ArrayList<Map<String , Integer>> myMap  = new ArrayList<Map<String,Integer>>();
		myMap.add(0,map1);
		myMap.add(1,map2);
		myMap.add(2,map3);
		myMap.add(3,map4);
		myMap.add(4,map5);
		myMap.add(5,map6);
		myMap.add(6,map7);
		myMap.add(7,map8);
		myMap.add(8,map9);
		myMap.add(9,map10);
		
		int j=0;
		int[] occurCVPR = new int[MAGAZINE_MAX_NUM];
		int[] occurICCV = new int[MAGAZINE_MAX_NUM];
		int[] occurECCV = new int[MAGAZINE_MAX_NUM];
		for (Map<String, Integer> m : myMap) {
	        occurCVPR[j] = (m.get("CVPR"));
	        occurICCV[j] = (m.get("ICCV"));
	        occurECCV[j++] = (m.get("ECCV"));
	    }
		
		request.setAttribute("keyword", keywords);
		request.setAttribute("CVPR", occurCVPR);
		request.setAttribute("ICCV", occurICCV);
		request.setAttribute("ECCV", occurECCV);
		request.getRequestDispatcher("index_three_hot.jsp").forward(request, response);
	}
}
