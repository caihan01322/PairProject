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

}
