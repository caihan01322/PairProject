package com.jdzy.papersearch;

import com.jdzy.papersearch.dao.KeywordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class PaperSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaperSearchApplication.class, args);
	}

}
