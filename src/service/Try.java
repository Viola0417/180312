package service;

import dao.Student_Dao;
import entity.Student;
import service.ReadExcel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Try {
	public static void main(String[] args) throws Exception {
		Student_Dao s_dao = new Student_Dao();
		//ParseExcel parser = new ParseExcel();
		InputStream inputStream = new FileInputStream("stu.xlsx");
		String suffix = "xlsx";
		int startRow = 1;
	
		//List<String[]> result = 
					//parser.parseExcel(inputStream, suffix, startRow);
		ReadExcel xlsMain = new ReadExcel();
		//Student student = null;
		List<Student> list = xlsMain.readXls(inputStream,suffix,startRow);
		
		System.out.println("执行结束");
		
		for(Student s : list){
			System.out.println(s.toString());
			s_dao.addStudent(s);
			
		}
		System.out.println("执行结束");
		
		
	}
}
