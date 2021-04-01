package com.eepractice.webcrawller.utils;

import com.eepractice.webcrawller.bean.ReadInformation;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class ReaderUtils {
    /**
     * 读取txt文件
     * @param FilePath
     * @return
     */
    public ReadInformation readUploadedTxtFile(String FilePath)  {
        int n = 0;
        ReadInformation info=new ReadInformation(null,null,null);
        try {
            File f = new File(FilePath);
            List<String> list=new ArrayList<>();
            if (f.isFile() && f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String str = "";
                while ((str = br.readLine()) != null)
                {
                    if(str!=""&&str!=null&&str.length()>0&&!str.replaceAll(" ","").equals("")){
                        list.add(str);
                        n++;
                    }
                }
                if(n==0){
                    info.setCode(201);
                    info.setData(null);
                    info.setMsg("文本内容为空");
                }
                else   if (n>10){
                    info.setCode(202);
                    info.setData(null);
                    info.setMsg("文本内容大于十行");
                }
                else {
                    info.setCode(200);
                    info.setData(list);
                    info.setMsg("成功");
                }
                br.close();
                fr.close();
            }
            else {
                System.out.println("找不到指定的文件");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 读取excel文件
     */
    public  ReadInformation readUploadedExcelFile(String inputFilePath) throws IOException {
        FileInputStream fileInput = new FileInputStream(inputFilePath);
        XSSFWorkbook wb = new XSSFWorkbook(fileInput);
        XSSFSheet sheet = wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        int realRowNum=0;
        ReadInformation info=new ReadInformation();
        String str="";
        List<String>list=new ArrayList<String>();
        if ((lastRowNum+1)==0){
            info.setMsg("EXCEL表格内容为空");
            info.setData(null);
            info.setCode(201);
        }
        else{
            for (int i = 0; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                str=cell.getStringCellValue();
                if (!str.replaceAll(" ","").equals("")&&str!=null){
                    list.add(str);
                    realRowNum++;
                }
            }
            if(realRowNum>10){
                info.setCode(202);
                info.setData(null);
                info.setMsg("EXCEL表格内容大于10行");
            }
            else if (realRowNum==0){
                info.setMsg("EXCEL表格内容为空");
                info.setData(null);
                info.setCode(201);
            }
            else {
                info.setCode(200);
                info.setData(list);
                info.setMsg("成功");
            }
        }
        wb.close();
        fileInput.close();
        return info;
    }
}
