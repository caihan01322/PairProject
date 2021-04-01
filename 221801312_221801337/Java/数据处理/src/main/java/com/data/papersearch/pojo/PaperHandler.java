package com.data.papersearch.pojo;

import com.alibaba.fastjson.JSONObject;

import java.io.File;

public interface PaperHandler {
	public Paper toPaper(JSONObject jo, String meetName, File file);
}
