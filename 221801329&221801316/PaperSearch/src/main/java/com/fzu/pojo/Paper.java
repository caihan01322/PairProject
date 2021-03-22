package com.fzu.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class Paper {
    String title;
    String abstractContent;
    String meet;
    Integer year;
    String link;
    List<String> keywords;
    List<String> author;
}
