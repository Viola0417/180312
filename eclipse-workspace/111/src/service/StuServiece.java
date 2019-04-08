package service;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Student_Dao;
import util.DbUtil;
import entity.Student;

public class StuServiece {
	public static List<Student> getAllStuById(){
		List<Student> list = new ArrayList<Student>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "select * from Student";
			Student_Dao student_dao = new Student_Dao();
			String[] str=null;
			ResultSet rs = student_dao.SearchStudent(sql, str);
			while(rs.next()) {
				int s_id = rs.getInt("s_id");
				String s_name = rs.getString("s_name");
				String s_password=rs.getString("s_password");
				System.out.println(s_id+s_name+s_password);
				list.add(new Student(s_id,s_name,s_password));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*public static List<Student> getAllByExcel(String file){
		List<Student> list=new ArrayList<Student>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
		}
	}*/
}
