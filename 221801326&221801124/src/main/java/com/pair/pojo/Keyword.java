package com.pair.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    private String kid;
    private String keyword;
    private String publisher;
    private int num;
}
