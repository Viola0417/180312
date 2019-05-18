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
			String sql=""+"insert into log"+"(stu_id,task_id,description,algo,F,R,P,time)"+"values("+"?,?,?,?,?,?,?,now())";
		//��sql�����ص��������򣬵���ִ��
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
			ptmt.setInt(1, l.getStu_id());
			ptmt.setInt(2, l.getTask_id());
			ptmt.setString(3, l.getDescription());
			ptmt.setString(4, l.getAlgo());
			ptmt.setDouble(5, l.getF());
			ptmt.setDouble(6, l.getR());
			ptmt.setDouble(7, l.getP());
		//ִ��sql���
			ptmt.execute();
			ptmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("�������Ѿ�������");
		}
	}
	
	public void delLog(int stu_id) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from log "+" where stu_id=?";
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		ptmt.setInt(1,stu_id);
		//ִ��sql���
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	
	public void delLogbyTask(int task_id) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from log "+" where task_id=?";
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		ptmt.setInt(1,task_id);
		//ִ��sql���
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	
	public List<Log> query() throws SQLException {
		System.out.println("�������ݿ������е������¼");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
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
			l.setAlgo(rs.getString("algo"));
			l.setTime(rs.getTimestamp("time"));
			log_list.add(l);
		}		
		ptmt.close();
		conn.close();
		return log_list;
	}
	
	//����task_id�ж�����⹲�ж��ٴ�����
	public int CheckLogByTask(int task_id) throws SQLException {
		int res = 0;
		System.out.println("���������ɹ������Ŀ���ٴ�");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(*) from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		ptmt.close();
		conn.close();
		//System.out.println("select count����ǣ�"+res);
		return res;
	}
	
	//����task_id�ж�����⹲�ж��ٸ���ͬ������
	public int CheckDisLogByTask(int task_id) throws SQLException {
		int res = 0;
		System.out.println("��������ж��ж�������ɹ������Ŀ");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(DISTINCT stu_id) from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		ptmt.close();
		conn.close();
		//System.out.println("select count����ǣ�"+res);
		return res;
	}
	public int CheckLogByStu(int stu_id) throws SQLException {
		int res = 0;
		System.out.println("����ѧ���жϸ�ͬѧ�������¼");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(*) from log where stu_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,stu_id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		ptmt.close();
		conn.close();
		//System.out.println("select count����ǣ�"+res);
		return res;
	}
	
	
	public List<Log> QueryByTaskNo(int task_id) throws SQLException {
		System.out.println("������Ŀ��Ų������ݿ��������¼");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
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
			l.setAlgo(rs.getString("algo"));
			l.setTime(rs.getTimestamp("time"));
			log_list.add(l);
		}		
		ptmt.close();
		conn.close();
		return log_list;
	}
	
	public List<Log> QueryByStuNo(int stu_id) throws SQLException {
		System.out.println("������Ŀ��Ų������ݿ��������¼");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from log where stu_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,stu_id);
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
			l.setAlgo(rs.getString("algo"));
			l.setTime(rs.getTimestamp("time"));
			log_list.add(l);
		}		
		ptmt.close();
		conn.close();
		return log_list;
	}
	
	//��ѯĳһ�����е�F(�������Լ�)
	public List<Double> QueryFbyTask(int task_id,int log_id) throws SQLException {
		System.out.println("������Ŀ��Ų������ݿ���F");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select F from log where task_id="+"?"+" and log_id<"+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ptmt.setInt(2, log_id);
		ResultSet rs=ptmt.executeQuery();
		List<Double> F_list = new ArrayList<Double>();
		while(rs.next()) {
			F_list.add(rs.getDouble("F"));
		}		
		ptmt.close();
		conn.close();
		return F_list;
	}
	
	//��ѯĳһ���log���������Լ���һ��
	public List<Log> QueryLogByTask(int task_id,int log_id) throws SQLException {
		System.out.println("������Ŀ��Ų������ݿ��������¼");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from log where task_id="+"?"+" and log_id<"+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ptmt.setInt(2, log_id);
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
			l.setAlgo(rs.getString("algo"));
			l.setTime(rs.getTimestamp("time"));
			log_list.add(l);
		}		
		ptmt.close();
		conn.close();
		return log_list;
	}
	
	//����log���������һ����¼��ֵ
	public Log CheckLastLog() throws SQLException {
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from log order by log_id desc LIMIT 1";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			l = new Log();
			l.setLog_id(rs.getInt("log_id"));
			l.setStu_id(rs.getInt("stu_id"));
			l.setTask_id(rs.getInt("task_id"));
			l.setF(rs.getDouble("F"));
			l.setP(rs.getDouble("P"));
			l.setR(rs.getDouble("R"));
			l.setDescription(rs.getString("description"));
			l.setAlgo(rs.getString("algo"));
			l.setTime(rs.getTimestamp("time"));
		}	
		ptmt.close();
		conn.close();
		return l;
	}
	
	public List<Double> QueryF(int task_id) throws SQLException {
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select F from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ResultSet rs=ptmt.executeQuery();
		List<Double> F_list = new ArrayList<Double>();
		while(rs.next()) {
			F_list.add(rs.getDouble("F"));
		}		
		ptmt.close();
		conn.close();
		return F_list;
	}
	
	//�õ�ĳ��ѧ����������ֵF�Ͷ�Ӧ��ѧ��
	public List<Log> QueryMaxF(int task_id) throws SQLException {
		List<Log> log_list = new ArrayList<Log>();
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql="select MAX(F),stu_id from log where task_id="+"?"+" group by stu_id";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			l = new Log();
			l.setStu_id(rs.getInt("stu_id"));
			l.setF(rs.getDouble("MAX(F)"));
			log_list.add(l);
		}
		ptmt.close();
		conn.close();
		return log_list;
	}
	
	//����task_id�ҵ�����Ӧ��Algo
	public List<String> QueryAlgobyTask(int task_id) throws SQLException {
		List<String> algo_list = new ArrayList<String>();
		Connection conn=DbUtil.getConnection();
		//System.out.println("���ݿ����ӳɹ�");
		String sql="select algo from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		String algo = null;
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			algo = rs.getString("algo");
			algo_list.add(algo);
		}
		ptmt.close();
		conn.close();
		List<String> new_list = new ArrayList<String>();
		for(String s:algo_list) {
			if(s!=null) {
				new_list.add(s);
			}
		}
		return new_list;
	}
}