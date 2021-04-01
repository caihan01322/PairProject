package com.data.papersearch;

import com.data.papersearch.service.Core;
import com.data.papersearch.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.*;

@SpringBootApplication()
@PropertySource(value={"application-local.properties"})

public class PapersearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(PapersearchApplication.class, args);

	}
	@Bean(name = "iccv")
	public Core getIccv(){
		return new Core("./论文数据/ICCV", "ICCV", Paper.getDefultPaperHandler());
	}
	@Bean(name = "cvpr")
	public Core getCvpr(){
		return new Core("./论文数据/CVPR", "CVPR", Paper.getDefultPaperHandler());
	}
	@Bean(name = "eccv")
	public Core getEccv(){
		return new Core("./论文数据/ECCV", "ECCV", Paper.getECCVPaperHandler());
	}
	@Autowired
	Core iccv;
	@Autowired
	Core cvpr;
	@Autowired
	Core eccv;
	@PostConstruct
	public void init() throws IOException {
		iccv.start();
		cvpr.start();
		eccv.start();
	}

}
