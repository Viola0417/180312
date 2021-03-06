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
			ptmt.close();
			conn.close();
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
			ptmt.close();
			conn.close();
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
		ptmt.close();
		conn.close();
		return task;
	}
	
	//根据题目类型找到题目的id
	public List<Integer> queryIDbyKind(String task_kind) throws SQLException {
		List<Integer> task_id_list = new ArrayList<Integer>();
		int task_id = 0;
		Connection conn=DbUtil.getConnection();
		String sql="select id from task where kind = "+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,task_kind);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			task_id = rs.getInt("id");
			task_id_list.add(task_id);
		}		
		ptmt.close();
		conn.close();
		return task_id_list;
	}
	
	//删除task
	public void delTask(int id) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from task "+" where id=?";
		//将sql语句加载到驱动程序，但不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
		ptmt.setInt(1,id);
		//执行sql语句
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	
	//根据task_id判断这道题是否存在
	public int CheckLogByTask(int id) throws SQLException {
		int res = 0;
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(*) from task where id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		ptmt.close();
		conn.close();
		System.out.println("select count结果是："+res);
		return res;
	}

}

