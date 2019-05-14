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
			System.out.println("在向数据库写入做题记录");
			Connection conn=DbUtil.getConnection();
			String sql=""+"insert into log"+"(stu_id,task_id,description,algo,F,R,P,time)"+"values("+"?,?,?,?,?,?,?,now())";
		//将sql语句加载到驱动程序，但不执行
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
			ptmt.setInt(1, l.getStu_id());
			ptmt.setInt(2, l.getTask_id());
			ptmt.setString(3, l.getDescription());
			ptmt.setString(4, l.getAlgo());
			ptmt.setDouble(5, l.getF());
			ptmt.setDouble(6, l.getR());
			ptmt.setDouble(7, l.getP());
		//执行sql语句
			ptmt.execute();
		}catch(Exception e) {
			System.out.println("该数据已经存在了");
		}
	}
	
	public void delLog(int stu_id) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from log "+" where stu_id=?";
		//将sql语句加载到驱动程序，但不执行
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
		ptmt.setInt(1,stu_id);
		//执行sql语句
		ptmt.execute();
	}
	public List<Log> query() throws SQLException {
		System.out.println("查找数据库里所有的做题记录");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
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
		return log_list;
	}
	
	//根据task_id判断这道题共有多少次做答
	public int CheckLogByTask(int task_id) throws SQLException {
		int res = 0;
		System.out.println("根据题号完成过这道题目多少次");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(*) from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		//System.out.println("select count结果是："+res);
		return res;
	}
	
	//根据task_id判断这道题共有多少个不同人做答
	public int CheckDisLogByTask(int task_id) throws SQLException {
		int res = 0;
		System.out.println("根据题号判断有多少人完成过这道题目");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(DISTINCT stu_id) from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		//System.out.println("select count结果是："+res);
		return res;
	}
	public int CheckLogByStu(int stu_id) throws SQLException {
		int res = 0;
		System.out.println("根据学号判断该同学的做题记录");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(*) from log where stu_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,stu_id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		//System.out.println("select count结果是："+res);
		return res;
	}
	
	
	public List<Log> QueryByTaskNo(int task_id) throws SQLException {
		System.out.println("根据题目编号查找数据库里做题记录");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
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
		return log_list;
	}
	
	public List<Log> QueryByStuNo(int stu_id) throws SQLException {
		System.out.println("根据题目编号查找数据库里做题记录");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
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
		return log_list;
	}
	
	//查询某一题所有的F(不包括自己)
	public List<Double> QueryFbyTask(int task_id,int log_id) throws SQLException {
		System.out.println("根据题目编号查找数据库里F");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
		String sql=""+"select F from log where task_id="+"?"+" and log_id<"+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ptmt.setInt(2, log_id);
		ResultSet rs=ptmt.executeQuery();
		List<Double> F_list = new ArrayList<Double>();
		while(rs.next()) {
			F_list.add(rs.getDouble("F"));
		}		
		return F_list;
	}
	
	//查询某一题的log，不包括自己这一条
	public List<Log> QueryLogByTask(int task_id,int log_id) throws SQLException {
		System.out.println("根据题目编号查找数据库里做题记录");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
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
		return log_list;
	}
	
	//查找log表里面最后一条记录的值
	public Log CheckLastLog() throws SQLException {
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
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
		return l;
	}
	
	public List<Double> QueryF(int task_id) throws SQLException {
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
		String sql=""+"select F from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		ResultSet rs=ptmt.executeQuery();
		List<Double> F_list = new ArrayList<Double>();
		while(rs.next()) {
			F_list.add(rs.getDouble("F"));
		}		
		return F_list;
	}
	
	//得到某个学生本题的最大值F和对应的学号
	public List<Log> QueryMaxF(int task_id) throws SQLException {
		List<Log> log_list = new ArrayList<Log>();
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
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
		
		return log_list;
	}
	
	//根据task_id找到它对应的Algo
	public List<String> QueryAlgobyTask(int task_id) throws SQLException {
		List<String> algo_list = new ArrayList<String>();
		Connection conn=DbUtil.getConnection();
		//System.out.println("数据库连接成功");
		String sql="select algo from log where task_id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,task_id);
		String algo = null;
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			algo = rs.getString("algo");
			algo_list.add(algo);
		}
		
		return algo_list;
	}
}