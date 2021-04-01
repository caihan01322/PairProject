package com.example.demo11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PageModel
{
    public int totalNum;
    public int CurrentNum;
    public List<FullArtical>list;

}
