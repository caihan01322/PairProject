package com.jdzy.papersearch.service;

import com.jdzy.papersearch.dao.AuthorDao;
import com.jdzy.papersearch.dao.KeywordDao;
import com.jdzy.papersearch.dao.PaperDao;
import com.jdzy.papersearch.pojo.Author;
import com.jdzy.papersearch.pojo.Keyword;
import com.jdzy.papersearch.pojo.Paper;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaperSearchService {
	@Autowired
	KeywordDao kDao;
	@Autowired
	PaperDao pDao;
	@Autowired
	AuthorDao aDao;
	@Autowired
	PaperSearchService service;

	@Cacheable(value = "similarWord",
			key="'word_'+#word+'_'+#limit")
	public List<Keyword> searchSimilarWord(String word,Integer limit){
		if(limit==null){
			limit=5;
		}

		String[] split = word.split(" |\\+");
		HashSet<Keyword> set = new LinkedHashSet<>();
		List<Keyword> similarKeyword = kDao.findSimilarKeyword(word,limit);
		set.addAll(kDao.findSimilarKeyword(word,limit));
		for (String s : split) {
			if(set.size()>=limit){
				break;
			}
			set.addAll(kDao.findSimilarKeyword(s,limit));
		}
		List<Keyword> strings = new ArrayList<>(set);

		limit=Math.min(limit,strings.size());
		ArrayList<Keyword> res = new ArrayList<>(strings.subList(0, limit));
		return res;
	}

	@Cacheable(value = "searchPaper",
			key="'paper_'+#searchWord+'_'+#orderBy+'_'+#time+'_'+#meetId+'_'+#page")
	public Map<String,Object> searchPaper(String searchWord,Integer orderBy,Integer time,Integer meetId,Integer limit,Integer page){
		Map<String, Object> resp = new HashMap<>();
		List<Map<String,Object>> paperList= new ArrayList<>();
		Date years = null;
		if(time!=null){
			int year = Calendar.getInstance().get(Calendar.YEAR);
			year-=time;
			try {
				years = new SimpleDateFormat("yyyy").parse(String.valueOf(year));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Integer pages=null;
		if(limit!=null&&page!=null){
			pages=limit*page;
		}
		List<Paper> papers = pDao.findPaperByWord(searchWord, orderBy, years, meetId, limit, pages);
		for (Paper paper : papers) {
			Map<String, Object> node = new HashMap<>();
			List<Author> authors = new ArrayList<>(aDao.findAuthorByPaperId(paper.getId(),15));
			List<Keyword> keywords = new ArrayList<>(kDao.findKeywordByPaperId(paper.getId(),null));
			node.put("paper",paper);
			node.put("authors",authors);
			node.put("keywords",keywords);
			paperList.add(node);
		}
		resp.put("paperList",paperList);
		resp.put("count",service.searchPaperCount(searchWord,years,meetId));
		return resp;
	}
	@Cacheable(value = "searchPaper",
			key="'paper_'+#searchWord+'_'+#publicationYears+'_'+#meetId")
	public Integer searchPaperCount(String searchWord,Date publicationYears,Integer meetId){
		return pDao.findPaperCountByWord(searchWord,publicationYears,meetId);

	}


}
