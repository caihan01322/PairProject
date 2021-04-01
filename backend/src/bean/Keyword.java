package com.eepractice.webcrawller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关键词，key为关键词名字，value为其出现次数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keyword {
    private String key;
    private Integer value;
}
