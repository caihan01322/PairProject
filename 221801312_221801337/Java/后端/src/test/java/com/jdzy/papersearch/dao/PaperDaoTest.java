package com.jdzy.papersearch.dao;

import com.jdzy.papersearch.pojo.Paper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class PaperDaoTest {
	@Autowired
	PaperDao dao;

	@Test
	void findPaperByKeyword() throws ParseException {
		List<Paper> papers = dao.findPaperByKeywordId(6,2, new SimpleDateFormat("yyyy").parse("2007") ,null);
		Assert.assertEquals(85L,papers.size());
		Assert.assertEquals(3259,papers.get(0).getId());
	}
}