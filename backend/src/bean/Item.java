package com.eepractice.webcrawller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * 传送给前端的论文数据类型
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    public String id;
    public String title;
    public List<String> author;
    public String link;
    public List<String> keyword;
    public String note;
    public String conferenceDate;
    public String publisher;

}
