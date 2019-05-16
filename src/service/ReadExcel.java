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
 * 解析excel文件
 *
 */
public class ReadExcel {
	
	/**
	 * 解析文件的方法 
	 * @param inputStream 文件输入流, 要解析的excel文件输入流
	 * @param suffix 文件后缀名，xls/xlsx，代码决定使用什么方式解析excel（不同jar包）
	 * @param startRow 从第几行开始读取数据
	 * @return List<String[]> 集合中的一个元素对应一行解析的数据，元素为字符串数组类型，数组中的每个元素对应一个字段数据
	 * @throws IOException（找不到文件）
	 */
	public List<Student> readXls(InputStream inputStream, String suffix, int startRow)
			throws IOException{
		
		// 1. 定义excel对象变量
		Workbook workbook = null;
		
		// 2. 判断后缀，决定使用的解析方式，决定如何创建具体的对象
		if("xls".equals(suffix)){
			// 2003版excel
			workbook = new HSSFWorkbook(inputStream);
		}else if("xlsx".equals(suffix)){
			// 2007版excel
			workbook = new XSSFWorkbook(inputStream);
		}else{
			// 未知内容
			return null;
		}
		
		// 获取工作表，excel分为若干个表,sheet
		Sheet sheet = workbook.getSheetAt(0);
		
		if(sheet == null){//空表格
			return null;
		}
		
		//获取表格中最后一行行号
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("最后一行行号为："+lastRowNum);
		
		// 最后一行的行号>startRow(开始读的行)
		if(lastRowNum < startRow){
			return null;
		}
		
		
		//List<String[]> result = new ArrayList<String[]>();
		List<Student> stuList = new ArrayList<Student>();
		Row row = null;//行变量
		Cell cell = null;//单元格变量
		// 循环读取
		for(int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
			
			row = sheet.getRow(rowNum);
			if(row != null) {
				Student s = new Student();
				Cell name = row.getCell(0);
				Cell id = row.getCell(1);
				//Cell password = row.getCell(2);
				//初始化学生的密码就是他的学号
				s.setS_name(parseCell(name));
				s.setS_id(Integer.valueOf(parseCell(id)));
				s.setS_password(parseCell(id));
				stuList.add(s);

			}
		}
		
		List<Student> new_list = new ArrayList<Student>();
		for(int i=0;i<stuList.size();i++) {
			if(stuList.get(i)!=null) {
				new_list.add(stuList.get(i));
			}
		}
		//System.out.println(test_list);
		return new_list;
	}

	/**
	 * 解析单元格
	 * @return String 返回单元格数组
	 */
	private String parseCell(Cell cell){
		String cellStr = null;
		
		// 判断单元格的类型
		switch(cell.getCellType()){
			case HSSFCell.CELL_TYPE_STRING :
				// 字符串类型单元格
				cellStr = cell.getRichStringCellValue().toString();//真实的字符串的值
				break;
			case HSSFCell.CELL_TYPE_BLANK : 
				// 空数据
				cellStr = "";
				break;
			case HSSFCell.CELL_TYPE_NUMERIC :
				// 数学类型，包含日期、时间、数字
				// 判断日期类型
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					// 日期类型    年月日||时分
					SimpleDateFormat sdf = null;
					if(cell.getCellType() == HSSFDataFormat.getBuiltinFormat("h:mm")){
						// 时间
						sdf = new SimpleDateFormat("HH:mm");
					}else{
						// 日期
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					Date temp = cell.getDateCellValue();
					cellStr = sdf.format(temp);
				}else{
					// 普通数字
					double temp = cell.getNumericCellValue();
					// 数学格式化工具					
					DecimalFormat format = new DecimalFormat();
					// 查看单元格中的具体样式类型
					String formatStr = cell.getCellStyle().getDataFormatString();
					if(formatStr.equals("General")){
						// 定义格式化正则，使用具体有效数据进行格式化且只保留有效数字
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













