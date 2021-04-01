package com.eepractice.webcrawller.utils;

import com.eepractice.webcrawller.bean.Item;

import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
    public static List<Item> transformEntity(List<Object[]> targetEntityList){
        List<Item> resultList = new ArrayList<>();
        for (Object[] objects : targetEntityList) {
            String kwds = (String)objects[0];
            List<String> keywords = DataUtils.Str2List(kwds);
            Item item = new Item();
            item.setKeyword(keywords);
            resultList.add(item);
        }
        return resultList;
    }
}
