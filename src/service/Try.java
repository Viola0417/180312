package service;

import dao.Log_Dao;
import dao.Student_Dao;
import entity.F_stu_id;
import entity.Log;
import entity.Student;
import service.ReadExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Try {
	public static void main(String[] args) throws Exception {

        String f_path_2 = "D:\\t_answer.xlsx";
		//System.out.println("学生上传答案地址:"+f_path_2);
		InputStream inputStream_2 = new FileInputStream(f_path_2);
		String suffix = "xlsx";
		int startRow = 0;
	
		//List<String[]> result = 
					//parser.parseExcel(inputStream, suffix, startRow);
		ParseTest xlsMain = new ParseTest();

		
		List<String> standard_list =  xlsMain.readXls(inputStream_2,suffix,startRow);
		System.out.println(standard_list);
		System.out.println("结束");
		List<String> new_list = new ArrayList<String>();
		for(int i=0;i<standard_list.size();i++) {
			if((!standard_list.get(i).equals(""))&&(standard_list.get(i)!=null)) {
				new_list.add(standard_list.get(i));
			}
		}
		System.out.println(new_list);
	}
	
}
