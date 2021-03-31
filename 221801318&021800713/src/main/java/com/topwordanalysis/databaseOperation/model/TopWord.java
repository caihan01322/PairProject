package com.topwordanalysis.databaseOperation.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * TopWord类
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */

@NoArgsConstructor
@AllArgsConstructor
public class TopWord {
    private int id;
    private String topWord;
    private String type;
    private String year;

    public String getTopWord() {
        return topWord;
    }

    public void setTopWord(String topWord) {
        this.topWord = topWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
