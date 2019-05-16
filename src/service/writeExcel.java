package service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writeExcel {
	 public void we(List<String> list,String path) throws Exception
	    {
	        //创建工作簿
		 	int len = list.size();
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        //新建工作表
	        XSSFSheet sheet = workbook.createSheet("hello");
	        //创建行,0表示第一行
	        XSSFRow row = sheet.createRow(0);
	        String cell_content = null;
	        //创建单元格行号由row确定,列号作为参数传递给createCell;第一列从0开始计算
			for(int rowNum = 0; rowNum < len; rowNum++) {
				row = sheet.createRow(rowNum);
				XSSFCell cell = row.createCell(0);
				cell_content = list.get(rowNum);
				cell.setCellValue(cell_content);
				}
	        //XSSFCell cell = row.createCell(2);
	        //给单元格赋值
	        //cell.setCellValue("hello sheet");
	        //创建输出流
	        FileOutputStream fos = new FileOutputStream(new File(path));
	        workbook.write(fos);
	        //workbook.close();
	        fos.close();
	    }
}
