package test;

import java.util.ArrayList;

public class HotWord {
	private String wordString;
	private ArrayList<Integer> wordList;
	
	public String getWordString() {
		return wordString;
	}
	public void setWordString(String wordString) {
		this.wordString = wordString;
	}
	public ArrayList<Integer> getWordList() {
		return wordList;
	}
	
	
	public void setWordList(ArrayList<Integer> wordList) {
		this.wordList = wordList;
	}
	public HotWord() {
		this.wordString = null;
		this.wordList = new ArrayList<Integer>();
		wordList.add(0);
		wordList.add(0);
		wordList.add(0);
	}
	

}
