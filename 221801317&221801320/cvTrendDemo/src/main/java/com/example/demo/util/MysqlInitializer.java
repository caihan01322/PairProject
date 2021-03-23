package com.example.demo.util;

import com.example.demo.pojo.PaperKeyword;
import com.example.demo.service.PaperKeywordService;
import com.example.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pojo.Paper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class MysqlInitializer {

    public static final String basePath = "/Users/sarisemac/Downloads/论文数据/" ;

    @Autowired
    PaperService paperService;
    @Autowired
    PaperKeywordService paperKeywordService;

    public void insertPaper(String meeting,int begin,int end) throws IOException {
        PaperJSONParser jsonParser = PaperJSONParser.valueOf(meeting.trim().toUpperCase());

        String rootPath = basePath+meeting.trim().toUpperCase();
        File root = new File(rootPath);
        File[] paperPaths = root.listFiles();

        for (int i = begin; i < end && i < paperPaths.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            IOUtil.readToBuffer(paperPaths[i].getAbsolutePath(),stringBuilder);
            Paper paper = jsonParser.getPaperByJSON(stringBuilder);
            paperService.add(paper);

            int paperID = paperService.getID(paper);
            StringBuilder mainContent = new StringBuilder(paper.getAbstractContent()).append(paper.getTitle());
            TextSolver solver = new TextSolver(mainContent);
            Map<String, Long> wordFrequencyMap = solver.getOrderedWordFrequencyMap(10);
            for (Map.Entry wordFrequency: wordFrequencyMap.entrySet()) {
                PaperKeyword paperKeyword = new PaperKeyword();
                paperKeyword.setPaperID(paperID);
                paperKeyword.setMeeting(paper.getMeeting());
                paperKeyword.setYear(paper.getYear());
                paperKeyword.setKeyword((String) wordFrequency.getKey());
                paperKeyword.setFrequency((Long) wordFrequency.getValue());
                paperKeywordService.add(paperKeyword);
            }
            stringBuilder.setLength(0);
        }
    }

    //@RequestMapping("/initmysql")
    public void insertAllPaper(String meeting) throws IOException {
        PaperJSONParser jsonParser = PaperJSONParser.valueOf(meeting.trim().toUpperCase());

        String rootPath = basePath+meeting.trim().toUpperCase();
        File root = new File(rootPath);
        File[] paperPaths = root.listFiles();

        for (int i = 0;  i < paperPaths.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            IOUtil.readToBuffer(paperPaths[i].getAbsolutePath(),stringBuilder);
            Paper paper = jsonParser.getPaperByJSON(stringBuilder);
            paperService.add(paper);

            int paperID = paperService.getID(paper);


            StringBuilder mainContent = new StringBuilder(paper.getTitle());
            if (paper.getAbstractContent()!=null && !paper.getAbstractContent().isEmpty()){
                mainContent.append(paper.getAbstractContent());
            }

            TextSolver solver = new TextSolver(mainContent);
            Map<String, Long> wordFrequencyMap = solver.getOrderedWordFrequencyMap(10);
            for (Map.Entry wordFrequency: wordFrequencyMap.entrySet()) {
                PaperKeyword paperKeyword = new PaperKeyword();
                paperKeyword.setPaperID(paperID);
                paperKeyword.setMeeting(paper.getMeeting());
                paperKeyword.setYear(paper.getYear());
                paperKeyword.setKeyword((String) wordFrequency.getKey());
                paperKeyword.setFrequency((Long) wordFrequency.getValue());
                paperKeywordService.add(paperKeyword);
            }
            stringBuilder=null;
            mainContent=null;
            solver=null;
            wordFrequencyMap=null;
            paper=null;
        }
    }
}







