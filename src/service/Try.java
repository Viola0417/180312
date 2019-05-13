package service;

import dao.Log_Dao;
import dao.Student_Dao;
import entity.F_stu_id;
import entity.Log;
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
		//Log l = new Log();
		Log_Dao l_dao = new Log_Dao();
		
        calculation c = new calculation();
		//rank = c.Cal_Log_Rank(l);
		//rank = c.Cal_Stu_Rank(l);
		/*
		List<Integer> f = new ArrayList<Integer>();
		f=c.classify_F(task_id);
		String path = "C:\\Users\\think\\Downloads\\apache-tomcat-9.0.6-windows-x64 (1)\\apache-tomcat-9.0.6\\webapps\\JFC\\2.jpeg";
		pie p = new pie();
		p.generatePieChart(f, path);
		*/
		//multicolumn m = new multicolumn();
		//m.generateColumnChart();
		List<F_stu_id> fs_list = new ArrayList<F_stu_id>();
		fs_list = c.calRankByTask(66);
		for(F_stu_id f:fs_list) {
			System.out.println(f.toString());
		}
		System.out.println("Ö´ÐÐ½áÊø");
	}
}
