package service;

import entity.Student;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ����excel�ļ�
 *
 */
public class ParseTest {
	
	/**
	 * �����ļ��ķ��� 
	 * @param inputStream �ļ�������, Ҫ������excel�ļ�������
	 * @param suffix �ļ���׺����xls/xlsx���������ʹ��ʲô��ʽ����excel����ͬjar����
	 * @param startRow �ӵڼ��п�ʼ��ȡ����
	 * @return List<String[]> �����е�һ��Ԫ�ض�Ӧһ�н��������ݣ�Ԫ��Ϊ�ַ����������ͣ������е�ÿ��Ԫ�ض�Ӧһ���ֶ�����
	 * @throws IOException���Ҳ����ļ���
	 */
	public List<String> readXls(InputStream inputStream, String suffix, int startRow)
			throws IOException{
		
		// 1. ����excel�������
		Workbook workbook = null;
		
		// 2. �жϺ�׺������ʹ�õĽ�����ʽ��������δ�������Ķ���
		if("xls".equals(suffix)){
			// 2003��excel
			workbook = new HSSFWorkbook(inputStream);
		}else if("xlsx".equals(suffix)){
			// 2007��excel
			workbook = new XSSFWorkbook(inputStream);
		}else{
			// δ֪����
			return null;
		}
		
		// ��ȡ������excel��Ϊ���ɸ���,sheet
		Sheet sheet = workbook.getSheetAt(0);
		
		if(sheet == null){//�ձ��
			return null;
		}
		
		//��ȡ��������һ���к�
		int lastRowNum = sheet.getLastRowNum();
		//System.out.println("���һ���к�Ϊ��"+lastRowNum);
		
		// ���һ�е��к�>startRow(��ʼ������)
		if(lastRowNum < startRow){
			return null;
		}
		List<String> test_list = new ArrayList<String>();
		Row row = null;//�б���
		for(int rowNum = startRow;rowNum <= lastRowNum;rowNum++) {
			row = sheet.getRow(rowNum);
			if(row != null) {
				Cell content = row.getCell(0);
				String s = parseCell(content);
				test_list.add(s);
			}
		}
		
		//System.out.println(test_list);
		return test_list;
	}

	/**
	 * ������Ԫ��
	 * @return String ���ص�Ԫ������
	 */
	private String parseCell(Cell cell){
		String cellStr = null;
		
		// �жϵ�Ԫ�������
		switch(cell.getCellType()){
			case HSSFCell.CELL_TYPE_STRING :
				// �ַ������͵�Ԫ��
				cellStr = cell.getRichStringCellValue().toString();//��ʵ���ַ�����ֵ
				break;
			case HSSFCell.CELL_TYPE_BLANK : 
				// ������
				cellStr = "";
				break;
			case HSSFCell.CELL_TYPE_NUMERIC :
				// ��ѧ���ͣ��������ڡ�ʱ�䡢����
				// �ж���������
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					// ��������    ������||ʱ��
					SimpleDateFormat sdf = null;
					if(cell.getCellType() == HSSFDataFormat.getBuiltinFormat("h:mm")){
						// ʱ��
						sdf = new SimpleDateFormat("HH:mm");
					}else{
						// ����
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					Date temp = cell.getDateCellValue();
					cellStr = sdf.format(temp);
				}else{
					// ��ͨ����
					double temp = cell.getNumericCellValue();
					// ��ѧ��ʽ������					
					DecimalFormat format = new DecimalFormat();
					// �鿴��Ԫ���еľ�����ʽ����
					String formatStr = cell.getCellStyle().getDataFormatString();
					if(formatStr.equals("General")){
						// �����ʽ������ʹ�þ�����Ч���ݽ��и�ʽ����ֻ������Ч����
						format.applyPattern("#");
					}
					cellStr = format.format(temp);
					//cellStr = ""+temp;
				}
				break;
			default : 
				cellStr = "";
		}
		
		return cellStr;
	}
}
