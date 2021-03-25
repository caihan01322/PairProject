package com.pairing.mapper;

import com.pairing.bean.Paper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperMapper {

    @Select("select * from data where keywords like concat('%', #{searchInfo}, '%') " +
            "or publicationTitle like concat('%', #{searchInfo}, '%') limit #{begin},4")
    public List<Paper> getPaper(@Param(value = "searchInfo") String searchInfo, @Param(value = "begin") int begin);

    @Select("select count(*) from data where keywords like concat('%', #{searchInfo}, '%') " +
            "or publicationTitle like concat('%', #{searchInfo}, '%')")
    public int getPaperCount(@Param(value = "searchInfo") String searchInfo);

    @Select(" select *,did as id from collect where collect.uid = #{userName} " +
            "and (keywords like concat('%', #{searchInfo}, '%') " +
            "or publicationTitle like concat('%', #{searchInfo}, '%')) limit #{begin},4")
    public List<Paper> getCollectPaper(@Param(value = "searchInfo") String searchInfo
            , @Param(value = "begin") int begin, @Param(value = "userName") String userName);

    @Select(" select count(*) from collect where collect.uid = #{userName} " +
            "and (keywords like concat('%', #{searchInfo}, '%') " +
            "or publicationTitle like concat('%', #{searchInfo}, '%'))")
    public int getCollectPaperCount(@Param(value = "searchInfo") String searchInfo
            , @Param(value = "userName") String userName);

    @Insert("insert into collect value(#{uid},#{did},#{publicationTitle}" +
            ",#{persistentLink},#{keywords},#{abstrac})")
    public Integer insertPaperToCollection(@Param("uid") String uid, @Param("did") String did
            , @Param("keywords") String keywords, @Param("abstrac") String abstrac
            , @Param("publicationTitle") String publicationTitle, @Param("persistentLink") String persistentLink);

}
