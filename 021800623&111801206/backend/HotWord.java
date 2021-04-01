package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HotWord {
	private String meetingName;
	private Map<String,Integer> wordMap;
	private ArrayList<Integer> numList;
	private ArrayList<String> wordList;
	
	public ArrayList<Integer> getNumList() {
		return numList;
	}

	public void setNumList(ArrayList<Integer> numList) {
		this.numList = numList;
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	public void setWordList(ArrayList<String> wordList) {
		this.wordList = wordList;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public Map<String, Integer> getWordMap() {
		return wordMap;
	}

	public void setWordMap(Map<String, Integer> wordMap) {
		this.wordMap = wordMap;
	}
	
	public HotWord() {
		this.meetingName = null;
		this.wordMap = new HashMap<String,Integer>();
		this.numList = new ArrayList<>();
		this.wordList = new ArrayList<>();
	}
	

}
