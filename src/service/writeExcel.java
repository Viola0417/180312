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
	        //����������
		 	int len = list.size();
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        //�½�������
	        XSSFSheet sheet = workbook.createSheet("hello");
	        //������,0��ʾ��һ��
	        XSSFRow row = sheet.createRow(0);
	        String cell_content = null;
	        //������Ԫ���к���rowȷ��,�к���Ϊ�������ݸ�createCell;��һ�д�0��ʼ����
			for(int rowNum = 0; rowNum < len; rowNum++) {
				row = sheet.createRow(rowNum);
				XSSFCell cell = row.createCell(0);
				cell_content = list.get(rowNum);
				cell.setCellValue(cell_content);
				}
	        //XSSFCell cell = row.createCell(2);
	        //����Ԫ��ֵ
	        //cell.setCellValue("hello sheet");
	        //���������
	        FileOutputStream fos = new FileOutputStream(new File(path));
	        workbook.write(fos);
	        //workbook.close();
	        fos.close();
	    }
}
