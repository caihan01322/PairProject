import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {


    /**
     * 读取file，把文件内容存在缓存中
     * 非法字符用空白字符代替
     * @param inputFilePath
     * @param stringBuffer
     * @return 非法字符数目
     * @throws IOException
     */
    public static int solveFileInASCII(String inputFilePath,StringBuilder stringBuffer) throws IOException {
        File inputFile = new File(inputFilePath);
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
        int unvalidCharNum = 0;
        for (int c = inputStream.read(); c!=-1; c = inputStream.read()) {
            char cur = (char) c;
            stringBuffer.append(cur);
            if (!(c>0&&c<128)) {
                unvalidCharNum++;
            }
        }
        return unvalidCharNum;
    }


    /**
     * 获取所有的字符，构成一个字符串
     * @param inputFilePath
     * @return
     * @throws IOException
     */
    public static String getString(String inputFilePath) throws IOException {
        File inputFile = new File(inputFilePath);
        StringBuilder textBuilder = new StringBuilder(64);
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        for (int c = fileInputStream.read(); c!=-1; c = fileInputStream.read()) {
            if (c>0&&c<128) {
                char cur = (char) c;
                textBuilder.append(cur);
            }
        }
        return textBuilder.toString();
    }

    /**
     * 按照acsii编码读取文件内容
     * @param inputFilePath 文件路径
     * @return {@code List<String>} 一项String代表一行
     */
    public static List<String> getStrings(String inputFilePath) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        File inputFile = new File(inputFilePath);
        InputStreamReader fileInputStream = new InputStreamReader(
                new FileInputStream(inputFile),"ascii");
        BufferedReader reader = new BufferedReader(fileInputStream);
        for (String temp = reader.readLine() ;temp !=null ; temp=reader.readLine()) {

            strings.add(temp);
        }
        reader.close();
        return strings;
    }

    /**
     * 按照utf-8的编码向文件写入文本
     * @param outputFilePath 文件路径
     * @param content 文本内容
     */
    public static void writeTo(String outputFilePath,String content){
        File outputFile = new File(outputFilePath);
        BufferedWriter writer=null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "utf-8"));
            writer.write(content);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("文件写入错误");
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("输出流关闭异常");
                }
            }
        }
    }

}
