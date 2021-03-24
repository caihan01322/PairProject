package com.pairing.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResponsesBody {
    private int count;
    private int code;
    private List<Paper> list = new ArrayList<>();
}
