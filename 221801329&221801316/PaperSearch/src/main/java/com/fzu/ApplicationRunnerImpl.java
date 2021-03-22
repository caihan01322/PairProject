package com.fzu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.fzu.pojo.Paper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    public List<Paper> cvprJsonParse() {
        List<Paper> paperList=new ArrayList<>();
        String dir=System.getProperty("user.dir");
        System.out.println(dir);
        File file=new File(dir+"/src/main/resources/论文数据/1");
        if(file.exists()){
            File []child=file.listFiles();
            for(int i=0;i<child.length;i++){
                Paper paper=new Paper();
                String json=jsonRead(child[i]);
                json=json.replace(";","");
                JSONObject jsonObject=JSONObject.parseObject(json);
                String title=jsonObject.getString("title");
                String abstractContent=jsonObject.getString("abstract");
                String link=jsonObject.getString("doiLink");
                String meet="CVPR";
                Integer year=jsonObject.getInteger("publicationYear");
                List<String> keywordList=new ArrayList<>();
                JSONArray keywords= jsonObject.getJSONArray("keywords");
                if(keywords!=null){
                    for(int j=0;j<keywords.size();j++){
                        JSONObject keyword=keywords.getJSONObject(j);
                        JSONArray jsonArray=keyword.getJSONArray("kwd");
                        for(int k=0;k<jsonArray.size();k++){
                            System.out.println(i+"      "+jsonArray.getString(k));
                            keywordList.add(jsonArray.getString(k));
                        }
                    }
                }
                else {
                    keywordList.add(" ");
                }
                List<String> authorList=new ArrayList<>();
                JSONArray authors=jsonObject.getJSONArray("authors");
                if(authors!=null){
                    for(int j=0;j<authorList.size();j++){
                        JSONObject author=authors.getJSONObject(j);
                        authorList.add(author.getString("name"));
                    }
                }
                else{
                    authorList.add(" ");
                }
                paper.setTitle(title);
                paper.setAbstractContent(abstractContent.substring(0,150));
                paper.setAuthor(authorList);
                paper.setKeywords(keywordList);
                paper.setLink(link);
                paper.setMeet(meet);
                paper.setYear(year);
                paperList.add(paper);
            }
        }
        else{
            System.out.println("文件不存在");
        }
        return paperList;
    }
    public String jsonRead(File file){
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (Exception e) {

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Paper> paperList=cvprJsonParse();
    }
}
