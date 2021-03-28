package com.example.demo11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artical
{
    public String title;
    public String link;
    public String Abstract;
    public int academicNum;
    public String magazine;
    public int year;
}
