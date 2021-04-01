package com.mapper;

import com.pojo.Keyword;
import com.pojo.MeetingInfo;
import com.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperMapper
{
    List<Keyword> getTopWords();
    List<Paper> getPapers();
    List<MeetingInfo> getMeetingInfo();

}
