package com.example.demo.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextSolver {
    String filePath;
    StringBuilder solveStringBulder;
    StringBuilder fileTextBuffer;
    List<String> strings;
    Map<String,Long> wordFrequencyMap;
    int validLineNum;
    int unvalidCharNum = 0;

    public TextSolver(StringBuilder fileTextBuffer) throws IOException {
        //数据初始化
        this.filePath = filePath;
        validLineNum=0;
        solveStringBulder = new StringBuilder();
        //fileTextBuffer = new StringBuilder(64);
        //unvalidCharNum = IOUtil.solveFileInASCII(filePath,fileTextBuffer);
        strings = Arrays.asList(fileTextBuffer.toString().split("\n"));
        strings.forEach(s -> solveString(s));

        //正则表达式切分字符串
        wordFrequencyMap = Arrays.asList(solveStringBulder.toString().split("\\s+"))
                .stream()
                .parallel()
                .filter(word->{ //过滤单词
                    if (word.length()<4) return false;
                    char[] chars = word.toCharArray();
                    for (int i = 0 ; i < 4 ; i++){
                        if (! Character.isLetter(chars[i])) return false;
                    }
                    for (char c : chars) {
                        if (c<0||c>=128) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.groupingBy(String::toLowerCase,Collectors.counting()));
    }

    public TextSolver(String filePath) throws IOException{
        //数据初始化
        this.filePath = filePath;
        validLineNum=0;
        solveStringBulder = new StringBuilder();
        fileTextBuffer = new StringBuilder(64);
        unvalidCharNum = IOUtil.readToBuffer(filePath,fileTextBuffer);
        strings = Arrays.asList(fileTextBuffer.toString().split("\n"));
        strings.forEach(s -> solveString(s));

        //正则表达式切分字符串
        wordFrequencyMap = Arrays.asList(solveStringBulder.toString().split("\\s+"))
                .stream()
                .parallel()
                .filter(word->{ //过滤单词
                    if (word.length()<4) return false;
                    char[] chars = word.toCharArray();
                    for (int i = 0 ; i < 4 ; i++){
                        if (! Character.isLetter(chars[i])) return false;
                    }
                    for (char c : chars) {
                        if (c<0||c>=128) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.groupingBy(String::toLowerCase,Collectors.counting()));
    }

    /**
     * 获取字符数
     * @return
     */
    public int getFileCharNum(){
        return fileTextBuffer.length()-unvalidCharNum;
    }

    /**
     * 获取单词数
     * @return
     */
    public long getWordNum(){
        long num = 0;
        for (Map.Entry<String,Long> pair: wordFrequencyMap.entrySet()) {
            num += pair.getValue();
        }
        return num;
    }

    /**
     * 获取有效行数
     * @return
     */
    public int getValidLineNum(){
        return validLineNum;
    }

    /**
     * 获取有序的前size个数据
     * @param size
     * @return
     */
    public Map<String,Long> getOrderedWordFrequencyMap(int size){
        return  wordFrequencyMap
                .entrySet() //获取set
                .stream()   //获取流
                .sorted(Map.Entry.<String, Long> comparingByValue() //按照数值排序，默认升序
                        .reversed()//倒序
                        .thenComparing(Map.Entry.comparingByKey()))//按照key排序
                .limit(size) //选择最前面的十个
                .collect(  //以map形式返回
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
    }

    /**
     * 统计有效行数
     * 将其中的非数字非英文字符转化为空白字符
     * 将转化后的字符串并入到solveStringBulder中用于进行单词统计
     * @param s
     */
    private void solveString(String s){
        if (s.trim().isEmpty()){       //空则返回
            return;
        }
        validLineNum++;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) { //非数字 非英文字符 转化为空白字符
            char cur = chars[i];
            if (!Character.isLetterOrDigit(cur)){
                chars[i] =' ';
            }
        }
        solveStringBulder.append(' ').append(chars);
    }
}
