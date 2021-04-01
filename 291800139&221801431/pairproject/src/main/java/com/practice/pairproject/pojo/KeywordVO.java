package com.practice.pairproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Data
@AllArgsConstructor
public class KeywordVO {
    private String content;
    private Integer total;
}
