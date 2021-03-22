package com.pair.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
    private String pid;
    private String title;
    private String abs;
    private String publisher;
    private String publicationYear;
    private String doiLink;
}
