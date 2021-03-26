package Entity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;


public class Essay {
    public String authors;//文章作者
    public String articlenumber;//文章编号
    public String keywords;//文章关键词
    public String link;//文章网址
    public String title;//文章题目
    public String summary;//摘要
    public String year;
    public String meeting;
    public String getAuthors() {
        return authors;
    }
    public String getArticleNumber() {
        return articlenumber;
    }
    public String getKeyWords() {
        return keywords;
    }
    public String getLink() {
        return link;
    }
    public String getTitle() {
        return title;
    }
    public String getSummary() {
        return summary;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public void setArticleNumber(String articleNumber) {
        this.articlenumber = articleNumber;
    }
    public void setKeyWords(String keyWords) {
        this.keywords = keyWords;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getYear() {
        return year;
    }
    public String getMeeting() {
        return meeting;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }
}
