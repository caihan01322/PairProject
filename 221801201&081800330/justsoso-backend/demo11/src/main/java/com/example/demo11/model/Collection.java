package com.example.demo11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

public class Collection
{
    public int totalPaperNum;
    public List<FullArtical>list;
    public Collection()
    {
        list = new ArrayList<FullArtical>();
        totalPaperNum = 0;
    }
}
