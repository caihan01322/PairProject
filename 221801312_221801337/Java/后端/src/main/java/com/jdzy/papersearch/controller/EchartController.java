package com.jdzy.papersearch.controller;

import com.jdzy.papersearch.service.EchartsService;
import com.jdzy.papersearch.tools.OpenApiTools;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api")
@Tag(name = "图表api")
public class EchartController {
    @Autowired
    EchartsService eService;

    @GetMapping(value = "/top10")
    @Operation(summary = "获取top10热词和包含对应热词的论文链接")
    @ResponseBody
    public Map<String,Object> getTop10(@RequestBody(required = false) Map<String,Object> req, HttpServletRequest http){
        return eService.getTop10();
    }

    @GetMapping(value = "/sunburst")
    @Operation(summary = "获取旭日图数据")
    @ResponseBody
    public Map<String,Object> getSunburst(@RequestBody(required = false) Map<String,Object> req, HttpServletRequest http){
        return eService.getSunburst();
    }
}
