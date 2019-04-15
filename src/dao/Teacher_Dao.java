package dao;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Teacher;

public class Teacher_Dao {
	
	public void addTeacher(Teacher t) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"insert into teacher"+"(name,password)"+"values("+"?,?)";
		//将sql语句加载到驱动程序，但不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
		ptmt.setString(1, t.getT_name());
		ptmt.setString(2, t.getT_password());
		//执行sql语句
		ptmt.execute();
	}
	public void updateTeacher(Teacher t) throws SQLException {
		//根据老师姓名修改老师密码
		Connection conn=DbUtil.getConnection();
		String sql=""+"update teacher "+"set password=? "+" where name=?";
		//将sql语句加载到驱动程序，但不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
		//ptmt.setString(1, t.getT_name());
		ptmt.setString(1, t.getT_password());
		ptmt.setString(2, t.getT_name());
		//执行sql语句
		ptmt.execute();
	}
	public void delTeacher(String name) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from teacher "+" where name=?";
		//将sql语句加载到驱动程序，但不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
		ptmt.setString(1,name);
		//执行sql语句
		ptmt.execute();
	}
	
	public List<Teacher> multiquery() throws SQLException{
		//System.out.println("--------------");
		//System.out.println("在dao文件");
		Connection conn=DbUtil.getConnection();
		Statement statement = conn.createStatement();
        String sql="select  * from teacher ";
        ResultSet set = statement.executeQuery(sql);//执行
        
        List<Teacher> tch = new ArrayList<Teacher>();
        Teacher t=null;
        while (set.next()){
        	t=new Teacher();
        	t.setT_name(set.getString("name"));
        	t.setT_password(set.getString("password"));
        	tch.add(t);
            //System.out.println(set.getString("name") + set.getString("password"));
        }
        //System.out.println("--------------");
		return tch;
		
	}
	public Teacher query(String name) throws SQLException {
		System.out.println("进入query函数");
		System.out.println(name);
		Teacher t = new Teacher();
		Connection conn=DbUtil.getConnection();
		System.out.println("数据库连接成功");
		String sql=""+"select * from teacher "+" where name=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, name);
		ResultSet rs=ptmt.executeQuery();
		//System.out.println(rs);
		while(rs.next()) {
			t=new Teacher();
			t.setT_name(rs.getString("name"));
			t.setT_password(rs.getString("password"));
			//System.out.println(t.getT_password());
		}
		return t;
	}
	
}
