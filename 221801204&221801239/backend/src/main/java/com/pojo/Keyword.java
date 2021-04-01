package com.pojo;

import org.apache.ibatis.type.Alias;

@Alias("Keyword")
public class Keyword
{
    private String name;//keyword
    private int value;//value

    public Keyword(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
