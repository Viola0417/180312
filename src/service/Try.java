package service;

import dao.Student_Dao;
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
		Log l = new Log();
		l.setF(0.3);
		l.setStu_id(20154488);
		l.setLog_id(20);
		l.setTask_id(53);
		calculation c = new calculation();
		int rank = 0;
		//rank = c.Cal_Log_Rank(l);
		rank = c.Cal_Stu_Rank(l);
		System.out.println("Ö´ÐÐ½áÊø");
	}
}
