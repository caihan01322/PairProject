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
    String name;
    String title;
    String dialCode;
}
