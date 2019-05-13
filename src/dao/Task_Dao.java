package dao;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Task;


public class Task_Dao {
	public void addTask(Task t) throws SQLException {
		try {
			Connection conn=DbUtil.getConnection();
			String sql=""+"insert into task"+"(title,description,kind)"+"values("+"?,?,?)";
		//将sql语句加载到驱动程序，但不执行
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
			ptmt.setString(1, t.getT_title());
			ptmt.setString(2, t.getT_description());
			ptmt.setString(3, t.getT_kind());
		//执行sql语句
			ptmt.execute();
		}catch(Exception e) {
			System.out.println("该数据已经存在了");
		}
	}
	
	public int SearchLastNum() {
		System.out.println("开始找最后一个题号");
		int id = 0;
		Task t = new Task();
		try {
			Connection conn=DbUtil.getConnection();
			String sql="select * from task where id=(SELECT max(id) FROM task)";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			ResultSet rs=ptmt.executeQuery();
			while(rs.next()) {
				t=new Task();
				t.setT_id(rs.getInt("id"));
				id = t.getT_id();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return id;
	}
	
	public List<Task> query() throws SQLException {
		System.out.println("进入query函数");
		Task t = new Task();
		Connection conn=DbUtil.getConnection();
		System.out.println("数据库连接成功");
		String sql=""+"select * from task ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		List<Task> task = new ArrayList<Task>();
		while(rs.next()) {
			t = new Task();
			t.setT_id(rs.getInt("id"));
			t.setT_title(rs.getString("title"));
			t.setT_description(rs.getString("description"));
			t.setT_kind(rs.getString("kind"));
			task.add(t);
		}		
		return task;
	}

}

