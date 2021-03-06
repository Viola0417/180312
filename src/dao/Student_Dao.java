package dao;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
//import entity.Teacher;
import entity.Teacher;

public class Student_Dao {
	public void addStudent(Student s) throws SQLException {
		try {
			Connection conn=DbUtil.getConnection();
			String sql=""+"insert into student"+"(name,id,password)"+"values("+"?,?,?)";
		//将sql语句加载到驱动程序，但不执行
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
			ptmt.setString(1, s.getS_name());
			ptmt.setInt(2, s.getS_id());
			ptmt.setString(3, s.getS_password());
		//执行sql语句
			ptmt.execute();
			ptmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("该数据已经存在了");
		}
	}
	
	public void updateStudent(Student s) throws SQLException {
		//根据学生学号修改学生密码
		Connection conn=DbUtil.getConnection();
		String sql=""+"update student "+"set password=? "+" where id=?";
		//将sql语句加载到驱动程序，但不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
		//ptmt.setString(1, t.getT_name());
		ptmt.setString(1, s.getS_password());
		ptmt.setInt(2, s.getS_id());
		//执行sql语句
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	
	public void delStudent(int id) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from student "+" where id=?";
		//将sql语句加载到驱动程序，但不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
		ptmt.setInt(1,id);
		//执行sql语句
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	
	public List<Student> multiquery() throws SQLException{
		//System.out.println("--------------");
		//System.out.println("在dao文件");
		Connection conn=DbUtil.getConnection();
		//Statement statement = conn.createStatement();
        String sql="select  * from student ";
		PreparedStatement ptmt=conn.prepareStatement(sql);

		ResultSet rs=ptmt.executeQuery();
        
        List<Student> stu = new ArrayList<Student>();
        Student s=null;
        while (rs.next()){
        	s=new Student();
        	s.setS_name(rs.getString("name"));
        	s.setS_id(rs.getInt("id"));
        	s.setS_password(rs.getString("password"));
        	stu.add(s);
            //System.out.println(set.getString("name") + set.getString("password"));
        }
		ptmt.close();
		conn.close();
        //System.out.println("--------------");
		return stu;
		
	}
	public Student query(int id) throws SQLException {
		System.out.println("进入query函数");
		System.out.println(id);
		Student s = new Student();
		Connection conn=DbUtil.getConnection();
		System.out.println("数据库连接成功");
		String sql=""+"select * from student "+" where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		//System.out.println(rs);
		while(rs.next()) {
			s=new Student();
			s.setS_name(rs.getString("name"));
			s.setS_id(rs.getInt("id"));
			s.setS_password(rs.getString("password"));
			//System.out.println(t.getT_password());
		}
		
		ptmt.close();
		conn.close();
		return s;
	}
	
	public List selectOne(Student s) throws SQLException {
		System.out.println("进入判断函数");
		List list = new ArrayList();
		//System.out.println(id);
		int id = s.getS_id();
		//Student s = new Student();
		Connection conn=DbUtil.getConnection();
		System.out.println("数据库连接成功");
		String sql=""+"select * from student "+" where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		//System.out.println(rs);
		while(rs.next()) {
			if(rs.getString("id").equals(s.getS_id())) {
				list.add(1);
			}
			else {
				list.add(0);
			}
		}
		ptmt.close();
		conn.close();
		return list;
	}
	
	public Student queryByName(String name) throws SQLException {
		System.out.println("进入query函数");
		System.out.println(name);
		Student s = new Student();
		Connection conn=DbUtil.getConnection();
		System.out.println("数据库连接成功");
		String sql=""+"select * from student "+" where name=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, name);
		ResultSet rs=ptmt.executeQuery();
		//System.out.println(rs);
		while(rs.next()) {
			s=new Student();
			s.setS_name(rs.getString("name"));
			s.setS_password(rs.getString("password"));
			s.setS_id(rs.getInt("id"));
			//System.out.println(t.getT_password());
		}
		ptmt.close();
		conn.close();
		return s;
	}
	
	public int CheckLogByStu(int id) throws SQLException {
		int res = 0;
		System.out.println("根据学号判断该同学是否存在");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(*) from student where id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		ptmt.close();
		conn.close();
		//System.out.println("select count结果是："+res);
		return res;
	}
}

