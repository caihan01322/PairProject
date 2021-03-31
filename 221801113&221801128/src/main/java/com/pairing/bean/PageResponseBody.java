package com.pairing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResponseBody {
    private String info;
    private int code;
    private int count;
    private List<Paper> list = new ArrayList<>();
}
