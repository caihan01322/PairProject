package com.pair.dao.impl;

import com.pair.dao.base.BaseDao;
import com.pair.dao.base.BaseDaoImpl;
import com.pair.model.Thesis;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository("thesisDao")
public class ThesisDaoImpl extends BaseDaoImpl<Thesis> implements BaseDao<Thesis> {

    private static RowMapper<Thesis> rowMapper;
    private static String sql = "select * from thesis";

    static {
        rowMapper = new RowMapper<Thesis>() {
            public Thesis mapRow(ResultSet rs, int rowNum) throws SQLException {
                Thesis thesis = new Thesis();
                thesis.setId(rs.getInt("id"));
                thesis.setContent(rs.getString("content"));
                thesis.setKeyword(rs.getString("keyword"));
                thesis.setLink(rs.getString("link"));
                return thesis;
            }
        };
    }

    public RowMapper<Thesis> getRowMapper() {
        return rowMapper;
    }

}
