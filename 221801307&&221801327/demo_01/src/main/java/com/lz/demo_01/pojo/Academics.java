package com.lz.demo_01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Academics implements Serializable {
    private int academicNum;
    private String author;
    private String title;
    private String keyword;
    private String magazine;
    private String abstra;
    private String link;
}
