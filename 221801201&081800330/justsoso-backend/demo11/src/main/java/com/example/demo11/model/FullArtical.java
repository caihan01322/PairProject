package com.example.demo11.model;

import lombok.Data;

import java.util.List;
@Data
public class FullArtical
{
    public  Artical artical;
    public  List<String> keywords;
    public  List<String> authors;
}
