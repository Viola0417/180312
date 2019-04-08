package dao;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import entity.Teacher;

public class Student_Dao {
	//查询
	public ResultSet SearchStudent(String sql,String str[]) {
		System.out.println("在查找学生");
		Connection conn=DbUtil.getConnection();
		ResultSet res=null;
		try {
			PreparedStatement ptmt=conn.prepareStatement(sql);
			if(str!=null) {
				for(int i=0;i<str.length;i++) {
					ptmt.setString(i+1, str[i]);
				}
			}
			res=ptmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//增删查改
	public int AddStudent(String sql,String str[]) {
		int a=0;
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement ptmt=conn.prepareStatement(sql);
			if(str!=null) {
				for(int i=0;i<str.length;i++) {
					ptmt.setString(i+1, str[i]);
				}
			}
			a=ptmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return a;
	}
}
