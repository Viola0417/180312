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
			String sql=""+"insert into log"+"(stu_id,task_id,description,F,R,P,time)"+"values("+"?,?,?,?,?,?,now())";
		//将sql语句加载到驱动程序，但不执行
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//给sql语句传递参数(从entity动态传参)
			ptmt.setInt(1, l.getStu_id());
			ptmt.setInt(2, l.getTask_id());
			ptmt.setString(3, l.getDescription());
			ptmt.setDouble(4, l.getF());
			ptmt.setDouble(5, l.getR());
			ptmt.setDouble(6, l.getP());
		//执行sql语句
			ptmt.execute();
		}catch(Exception e) {
			System.out.println("该数据已经存在了");
		}
	}

	public List<Log> query() throws SQLException {
		System.out.println("查找数据库里所有的做题记录");
		Log l = new Log();
		Connection conn=DbUtil.getConnection();
		System.out.println("数据库连接成功");
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
