package com.topwordanalysis.databaseOperation.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @ClassName: RealPaper
 * @Description:
 * @author: 黄贸之
 * @date: 2021/3/30 16:19
 * @Github: https://github.com/h2333
 */

@NoArgsConstructor
@AllArgsConstructor
public class RealPaper {
    private String title;
    private String link;
    private String paperAbstract;
    private String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}