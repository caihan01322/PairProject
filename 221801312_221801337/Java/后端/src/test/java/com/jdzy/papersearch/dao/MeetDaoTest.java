package com.jdzy.papersearch.dao;

import com.jdzy.papersearch.pojo.Meet;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
class MeetDaoTest {
    @Autowired
    MeetDao dao;
    @Test
    void getMeetList(){
        List<Meet> meetList = dao.getMeetList();
        Assert.assertEquals(3,meetList.size());
    }
}
