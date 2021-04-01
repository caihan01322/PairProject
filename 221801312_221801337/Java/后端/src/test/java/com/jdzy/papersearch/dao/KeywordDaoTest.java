package com.jdzy.papersearch.dao;

import com.jdzy.papersearch.pojo.Keyword;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.text.ParseException;

@SpringBootTest
class KeywordDaoTest {
	@Autowired
	KeywordDao dao;

	@Test
	void findKeywordIdByName() {
		Assert.assertEquals(Integer.valueOf(1),dao.findKeywordIdByName("Mirrors"));
		Assert.assertEquals(Integer.valueOf(12752),dao.findKeywordIdByName("Vision for graphics"));
	}
	@Test
	void findSimilarKeyword(){
		final List<Keyword> word = dao.findSimilarKeyword("2-",null);
		Assert.assertEquals(word.get(0).getKeyword(),"2-point motion estimation");
		Assert.assertEquals(word.get(1).getKeyword(),"2-sphere");
		Assert.assertEquals(word.get(2).getKeyword(),"AI2-THOR");
	}

	@Test
	void findTopKeyword() throws ParseException {
		List<Map<String, Object>> topKeyword = dao.findTopKeyword(1, new SimpleDateFormat("yyyy").parse("2001"),10);
		Assert.assertEquals(10,topKeyword.size());
		Assert.assertEquals(44L,topKeyword.get(0).get("keyword_num"));
	}
}