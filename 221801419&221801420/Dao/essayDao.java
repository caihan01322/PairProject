package Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PatchMapping;

import Entity.Essay;
import Entity.Keywords;

public interface essayDao {

    Essay getInfo(String essayName);
    void editcvpr(Essay essay);
    void editeccv(Essay essay);
    void editiccv(Essay essay);
    List<Essay> singleSearch(@Param("searchString") String searchString);
    List<Keywords> keywordsGetCVPR(@Param("year")String year);
    List<Keywords> keywordsGetECCV(@Param("year")String year);
    List<Keywords> keywordsGetICCV(@Param("year")String year);
    List<Keywords> keywordsGetMY(@Param("name")String name);
    List<Keywords> keywordsGetCVPRALL();
    List<Keywords> keywordsGetECCVALL();
    List<Keywords> keywordsGetICCVALL();
    List<Essay> labelSearchByTitle(@Param("title")String title,@Param("user")String user);
    List<Essay> labelSearchByNum(@Param("num")String num,@Param("user")String user);
    List<Essay> labelSearchByKeywords(@Param("words")String words,@Param("user")String user);
    int selectCheckCVPR(@Param("title")String essayName,@Param("user")String username);
    int selectCheckECCV(@Param("title")String essayName,@Param("user")String username);
    int selectCheckICCV(@Param("title")String essayName,@Param("user")String username);
}
