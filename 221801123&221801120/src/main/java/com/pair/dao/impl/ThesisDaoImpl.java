package com.pair.dao.impl;

import com.pair.dao.ThesisDao;
import com.pair.dao.base.BaseDao;
import com.pair.dao.base.BaseDaoImpl;
import com.pair.model.Thesis;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository("thesisDao")
public class ThesisDaoImpl extends BaseDaoImpl<Thesis> implements ThesisDao {

    private static RowMapper<Thesis> rowMapper;
    private static String sql = "select * from thesis";

    static {
        rowMapper = new RowMapper<Thesis>() {
            public Thesis mapRow(ResultSet rs, int rowNum) throws SQLException {
                Thesis thesis = new Thesis();
                thesis.setId(rs.getInt("id"));
                thesis.setAbstractContent(rs.getString("abstract_content"));
                thesis.setMeet(rs.getString("meet"));
                thesis.setTitle(rs.getString("title"));
                thesis.setYear(rs.getString("year"));
                thesis.setLink(rs.getString("link"));
                thesis.setKeyword(rs.getString("keyword"));
                return thesis;
            }
        };
    }

    public RowMapper<Thesis> getRowMapper() {
        return rowMapper;
    }

    public String getSql() {
        return sql;
    }

    public String getCountSql() {
        return "select count(id) from thesis";
    }

}
