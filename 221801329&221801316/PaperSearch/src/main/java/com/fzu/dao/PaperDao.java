package com.fzu.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperDao {
    public void upLoadData(JSONObject jsonObject);
}
