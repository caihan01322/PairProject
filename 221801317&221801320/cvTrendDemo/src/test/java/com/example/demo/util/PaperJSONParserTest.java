package com.example.demo.util;

import org.junit.jupiter.api.Test;
import pojo.Paper;

import java.io.IOException;
import java.util.Map;

public class PaperJSONParserTest {
    @Test
    public void testCVPR() throws IOException {
        String path = "/Users/sarisemac/Downloads/论文数据/CVPR/2020_Local Non-Rigid Structure-From-Motion From Diffeomorphic Mappings.json";
        //PaperJSONParser.CVPR.getPaper("/Users/sarisemac/Downloads/论文数据/CVPR/2020_Local Non-Rigid Structure-From-Motion From Diffeomorphic Mappings.json");
        StringBuilder stringBuilder = new StringBuilder();
        IOUtil.readToBuffer(path,stringBuilder);
        Paper paper = PaperJSONParser.CVPR.getPaperByJSON(stringBuilder);
        System.out.println(paper.getAuthors());
    }

    @Test
    public void testECCV() throws IOException {
        String path = "/Users/sarisemac/Downloads/论文数据/ECCV（2016至2020，3033份）/2016_3D Image Reconstruction from X-Ray Measurements with Overlap.json";
        //PaperJSONParser.CVPR.getPaper("/Users/sarisemac/Downloads/论文数据/CVPR/2020_Local Non-Rigid Structure-From-Motion From Diffeomorphic Mappings.json");
        StringBuilder stringBuilder = new StringBuilder();
        IOUtil.readToBuffer(path,stringBuilder);
        Paper paper = PaperJSONParser.ECCV.getPaperByJSON(stringBuilder);
        System.out.println(paper.getYear());
    }

    @Test
    public void testICCV() throws IOException {
        String path = "/Users/sarisemac/Downloads/论文数据/ICCV（2001年至2019年，3196篇）/2001_A statistical approach to background subtraction for surveillance systems.json";
        //PaperJSONParser.CVPR.getPaper("/Users/sarisemac/Downloads/论文数据/CVPR/2020_Local Non-Rigid Structure-From-Motion From Diffeomorphic Mappings.json");
        StringBuilder stringBuilder = new StringBuilder();
        IOUtil.readToBuffer(path,stringBuilder);
        Paper paper = PaperJSONParser.ICCV.getPaperByJSON(stringBuilder);

        StringBuilder mainContent = new StringBuilder(paper.getAbstractContent()).append(paper.getTitle());
        TextSolver solver = new TextSolver(mainContent);
        Map<String, Long> wordFrequencyMap = solver.getOrderedWordFrequencyMap(10);
        System.out.println(paper.getYear());
    }
}

