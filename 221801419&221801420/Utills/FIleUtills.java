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

	private final static String excel2003L=".xls";//2003�汾��excel
	private final static String execel2007U=".xlsx";//2007+�汾��excel
	
	/*
	 * ��ȡio���е����ݣ���װ��List<List<Object>>����
	 * 
	 */
	
	 public static List<List<Object>> getBankByExcel(InputStream in,String filename){
		List <List<Object>> list=null;
		
		//����excel������
		Workbook work=getWorkbook(in,filename);
		
		if (work==null) {
			System.out.print("������Ϊ��");
		}
		
		Sheet sheet=null;
		Row row=null;
		Cell cell=null;
		
		list =new ArrayList<List<Object>>();
		
		//����excel���е�sheet
		for (int i=0;i<work.getNumberOfSheets();i++) {
			sheet=work.getSheetAt(i);
			if (sheet==null) {continue;}
			
			//������ǰsheet�е�������
			for (int j=sheet.getFirstRowNum()-1;j<=sheet.getLastRowNum();j++) {
				row=sheet.getRow(j);
				if (row==null) {
					continue;
				}
				//�������е���
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
	 * �����ļ���׺��������Ӧ��workbook
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
					System.out.println("�ļ���ʽ����");
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return wb;
	}
	
	/*
	 * �Ա�����ݽ��и�ʽ��
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
