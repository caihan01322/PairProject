package com.topwordanalysis.databaseOperation.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @ClassName: TopWordResult
 * @Description:
 * @author: 黄贸之
 * @date: 2021/3/28 23:37
 * @Github: https://github.com/h2333
 */
@NoArgsConstructor
@AllArgsConstructor
public class TopWordResult {
    private String word;
    private String count;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}