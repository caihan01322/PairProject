package com.jdzy.papersearch.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jdzy.papersearch.pojo.Keyword;
import com.jdzy.papersearch.service.PaperSearchService;
import com.jdzy.papersearch.tools.HttpTools;
import com.jdzy.papersearch.tools.OpenApiTools;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Controller
@RequestMapping("/api")
@Tag(name = "搜索api")
public class PaperSearchController {

	@Autowired
	PaperSearchService searchService;
	@Autowired
	ThreadPoolExecutor threadPoolExecutor;

	@PostMapping("/search_word")
    @Operation(summary = "搜索框输入补全接口")
	@ResponseBody
	public Map<String,Object> searchWord(@Schema(defaultValue = OpenApiTools.searchWord)
											 @RequestBody Map<String,Object> req,
										 HttpServletRequest http){
		List<Keyword> searchWord = searchService.searchSimilarWord((String) req.get("searchWord"),(Integer) req.getOrDefault("limit",null));

		HashMap<String, Object> resp = new LinkedHashMap<>();
		resp.put("searchWord",searchWord);
		return resp;
	}

//	@PostMapping("/search_paper")
	@Operation(summary = "搜索文章接口")
	@ResponseBody
	public Map<String,Object> searchPaper(@Schema(defaultValue = OpenApiTools.searchPaper)
											  @RequestBody Map<String,Object> req,
										  HttpServletRequest http){
		Map<String,Object>resp=searchService.searchPaper((String)req.get("searchWord"),
				(Integer)req.get("orderBy"),
				(Integer)req.get("time"),
				(Integer)req.get("meetId"),
				(Integer)req.get("limit"),
				(Integer)req.get("page"));
		return resp;
	}
	@GetMapping("search_paper")
	@Operation(summary = "搜索文章接口")
	@ResponseBody
	public Map<String,Object> searchPaper(@Schema(defaultValue = "3D") String searchWord,
										  @Nullable Integer orderBy,
										  @Nullable @Schema(defaultValue = "5") Integer time,
										  @Nullable  Integer meetId,
										  @Schema(defaultValue = "0") Integer page,
										  HttpServletRequest http){
		if(page==null)page=0;
		Map<String,Object>resp=searchService.searchPaper(searchWord,
				orderBy,
				time,
				meetId,
				9,
				page);
		Integer finalPage = page;
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				searchService.searchPaper(searchWord,
						orderBy,
						time,
						meetId,
						9,
						finalPage +1);
			}
		});
		return resp;
	}
}
