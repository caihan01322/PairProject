package Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Entity.Essay;
import Entity.Keywords;

public interface essayDao {

    Essay getInfo(String essayName);

}
