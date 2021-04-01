package com.eepractice.webcrawller.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 读取文件返回数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadInformation {

    public Integer code;
    public String msg;
    public List<String> data;

}
