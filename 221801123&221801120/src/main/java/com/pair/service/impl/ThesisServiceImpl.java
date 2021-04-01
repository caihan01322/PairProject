package com.pair.service.impl;


import com.pair.dao.ThesisDao;
import com.pair.dao.base.BaseDao;
import com.pair.model.Thesis;
import com.pair.service.ThesisService;
import com.pair.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("thesisService")
public class ThesisServiceImpl extends BaseServiceImpl<Thesis> implements ThesisService {

    private ThesisDao thesisDao;

    @Resource(name = "thesisDao")

    @Override
    protected void setBaseDao(BaseDao<Thesis> baseDao) {
        super.baseDao = baseDao;
        this.thesisDao = (ThesisDao) baseDao;
    }

    @Override
    public void delete(Object id) {
        id = Integer.parseInt(id.toString());
        String[] sqls = {
                "delete from thesis where id = " + id + "",
                //和论文关联
                "DELETE FROM thesis_keyword WHERE tid = " + id + ""
        };
        thesisDao.batchUpdate(sqls);
    }

    @Override
    public void update(String id, String title, String meet, String year, String keyword, String abstractContent
            , String link) {
        String sql = "update thesis set title = ?, meet = ?, year = ?, keyword = ?, abstract_content = ?" +
                ", link = ? where id = ?";
        thesisDao.executeSql(sql, new Object[]{title, meet, year, keyword, abstractContent, link, id});
    }
}
