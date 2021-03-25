package com.pairing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worditem {

    String code;
    String emoji;
    String unicode;
    String name;//有用！！！！！！！！！！
    String title;
    String dialCode;
//    "code": "AD",
//            "emoji": "🇦🇩",
//            "unicode": "U+1F1E6 U+1F1E9",
//            "name": "Andorra",
//            "title": "flag for Andorra",
//            "dialCode": "+376"

}
