package com.pairing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Paper {
    private String id;
    private String publicationTitle;
    private String authors;
    private String keywords;
    private String abstrac;
    private String persistentLink;
    private String publicationYear;
    private String typepaper;
}
