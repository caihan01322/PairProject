package com.pair.service;


import com.pair.model.Thesis;
import com.pair.service.base.BaseService;

public interface ThesisService extends BaseService<Thesis> {

    /**
     * 更新论文
     */
    void update(String id, String title, String meet, String year, String keyword, String abstractContent
            , String link);
}
