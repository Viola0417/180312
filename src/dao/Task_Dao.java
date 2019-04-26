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
			String sql=""+"insert into task"+"(title,description)"+"values("+"?,?)";
		//��sql�����ص��������򣬵���ִ��
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
			ptmt.setString(1, t.getT_title());
			ptmt.setString(2, t.getT_description());
		//ִ��sql���
			ptmt.execute();
		}catch(Exception e) {
			System.out.println("�������Ѿ�������");
		}
	}
	
	public int SearchLastNum() {
		System.out.println("��ʼ�����һ�����");
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
		System.out.println("����query����");
		Task t = new Task();
		Connection conn=DbUtil.getConnection();
		System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from task ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		List<Task> task = new ArrayList<Task>();
		while(rs.next()) {
			t = new Task();
			t.setT_id(rs.getInt("id"));
			t.setT_title(rs.getString("title"));
			t.setT_description(rs.getString("description"));
			task.add(t);
		}		
		return task;
	}

}

