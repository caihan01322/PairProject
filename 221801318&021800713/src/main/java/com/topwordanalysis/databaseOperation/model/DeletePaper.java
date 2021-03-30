package com.topwordanalysis.databaseOperation.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DeletePaper
 * @Description:
 * @author: 黄贸之
 * @date: 2021/3/30 22:32
 * @Github: https://github.com/h2333
 */
@AllArgsConstructor
@NoArgsConstructor
public class DeletePaper {
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}