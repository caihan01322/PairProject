package com.geiyepa.demo.mapper;

import com.geiyepa.demo.bean.paper;
import com.geiyepa.demo.bean.paperWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface paperMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paper
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paper
     *
     * @mbg.generated
     */
    int insert(paperWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paper
     *
     * @mbg.generated
     */
    int insertSelective(paperWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paper
     *
     * @mbg.generated
     */
    paperWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paper
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(paperWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paper
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(paperWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table paper
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(paper record);

    /*

     通过关键词模糊搜索
     */
    List<paper> selectLikeWord(String word);

    List<paper> selectLikeKeyword(String keyword);

    paper getPaper(@Param("id") Integer id);
}