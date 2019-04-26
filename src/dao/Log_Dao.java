package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Log;
import entity.Task;
import util.DbUtil;

public class Log_Dao {
	public void addLog(Log l) throws SQLException {
		try {
			System.out.println("�������ݿ�д�������¼");
			Connection conn=DbUtil.getConnection();
			String sql=""+"insert into log"+"(stu_id,task_id,description,F,R,P,time)"+"values("+"?,?,?,?,?,?,now())";
		//��sql�����ص��������򣬵���ִ��
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
			ptmt.setInt(1, l.getStu_id());
			ptmt.setInt(2, l.getTask_id());
			ptmt.setString(3, l.getDescription());
			ptmt.setDouble(4, l.getF());
			ptmt.setDouble(5, l.getR());
			ptmt.setDouble(6, l.getP());
		//ִ��sql���
			ptmt.execute();
		}catch(Exception e) {
			System.out.println("�������Ѿ�������");
		}
	}

	public List<Log> query() throws SQLException {
		System.out.println("�������ݿ������е������¼");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from log ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		List<Log> log_list = new ArrayList<Log>();
		while(rs.next()) {
			l = new Log();
			l.setLog_id(rs.getInt("log_id"));
			l.setStu_id(rs.getInt("stu_id"));
			l.setTask_id(rs.getInt("task_id"));
			l.setF(rs.getDouble("F"));
			l.setP(rs.getDouble("P"));
			l.setR(rs.getDouble("R"));
			l.setDescription(rs.getString("description"));
			l.setTime(rs.getTimestamp("time"));
			log_list.add(l);
		}		
		return log_list;
	}
}
