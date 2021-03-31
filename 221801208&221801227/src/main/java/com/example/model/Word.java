package com.example.model;

import java.io.Serializable;

public class Word implements Comparable<Word>{

    public String word;     //单词
    public int frequency;    //单词出现次数

    public Word(String s, int frqy){
        word = s;
        frequency = frqy;
    }

    @Override
    public int compareTo(Word w) {
        if(this.frequency > w.frequency)
            return -1;
        else if(this.frequency < w.frequency)
            return 1;
        else
            return this.word.compareTo(w.word);
    }
}
