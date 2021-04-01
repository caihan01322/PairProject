package Utills;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FIleUtills {

	private final static String excel2003L=".xls";//2003版本的excel
	private final static String execel2007U=".xlsx";//2007+版本的excel
	
	/*
	 * 获取io流中的数据，组装成List<List<Object>>对象
	 * 
	 */
	
	 public static List<List<Object>> getBankByExcel(InputStream in,String filename){
		List <List<Object>> list=null;
		
		//创建excel工作普
		Workbook work=getWorkbook(in,filename);
		
		if (work==null) {
			System.out.print("工作簿为空");
		}
		
		Sheet sheet=null;
		Row row=null;
		Cell cell=null;
		
		list =new ArrayList<List<Object>>();
		
		//遍历excel所有的sheet
		for (int i=0;i<work.getNumberOfSheets();i++) {
			sheet=work.getSheetAt(i);
			if (sheet==null) {continue;}
			
			//遍历当前sheet中的所有行
			for (int j=sheet.getFirstRowNum()-1;j<=sheet.getLastRowNum();j++) {
				row=sheet.getRow(j);
				if (row==null) {
					continue;
				}
				//遍历所有的列
				List <Object> li=new ArrayList<Object>();
				for (int y=row.getFirstCellNum();y<row.getLastCellNum();y++) {
					cell=row.getCell(y);
					if (cell==null) {
						li.add(null);
					}else {
						li.add(getCellValue(cell));
					}
				}
				list.add(li);
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 根据文件后缀，生成相应的workbook
	 */
	public static Workbook getWorkbook(InputStream inStr,String fileName) {
		Workbook wb=null;
		String fileType=fileName.substring(fileName.lastIndexOf("."));
			try {
				if (excel2003L.equals(fileType)) {
					wb=new HSSFWorkbook(inStr);
				}else if (execel2007U.equals(fileType)) {
					wb=new XSSFWorkbook(inStr);
					
				}else {
					System.out.println("文件格式错误");
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return wb;
	}
	
	/*
	 * 对表格数据进行格式化
	 * 
	 */
	public static Object getCellValue(Cell cell) {
		Object value=null;
		DecimalFormat df=new DecimalFormat("0");
		SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
		DecimalFormat df2=new DecimalFormat("0.00");
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value=cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;
		case Cell.CELL_TYPE_BOOLEAN:
			value=cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value="";
			break;
		default:
			break;
		}
		return value;
	}
}
