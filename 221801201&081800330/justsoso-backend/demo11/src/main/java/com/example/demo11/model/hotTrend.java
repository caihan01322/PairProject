package com.example.demo11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class hotTrend
{
    public int _2014;
    public int _2015;
    public int _2016;
    public int _2017;
    public int _2018;
    public int _2019;
    public int _2020;
    public hotTrend()
    {
        _2015 = 0;
        _2014 = 0;
        _2016 = 0;
        _2017 = 0;
        _2018 = 0;
        _2019 = 0;
        _2020 = 0;
    }
}
