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
		//��sql�����ص��������򣬵���ִ��
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
			ptmt.setString(1, t.getT_title());
			ptmt.setString(2, t.getT_description());
			ptmt.setString(3, t.getT_kind());
		//ִ��sql���
			ptmt.execute();
			ptmt.close();
			conn.close();
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
			ptmt.close();
			conn.close();
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
			t.setT_kind(rs.getString("kind"));
			task.add(t);
		}		
		ptmt.close();
		conn.close();
		return task;
	}
	
	//������Ŀ�����ҵ���Ŀ��id
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
	
	//ɾ��task
	public void delTask(int id) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from task "+" where id=?";
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		ptmt.setInt(1,id);
		//ִ��sql���
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	
	//����task_id�ж�������Ƿ����
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
		System.out.println("select count����ǣ�"+res);
		return res;
	}

}

