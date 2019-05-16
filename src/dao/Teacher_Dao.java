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
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		ptmt.setString(1, t.getT_name());
		ptmt.setString(2, t.getT_password());
		//ִ��sql���
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	public void updateTeacher(Teacher t) throws SQLException {
		//������ʦ�����޸���ʦ����
		Connection conn=DbUtil.getConnection();
		String sql=""+"update teacher "+"set password=? "+" where name=?";
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		//ptmt.setString(1, t.getT_name());
		ptmt.setString(1, t.getT_password());
		ptmt.setString(2, t.getT_name());
		//ִ��sql���
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	public void delTeacher(String name) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from teacher "+" where name=?";
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		ptmt.setString(1,name);
		//ִ��sql���
		ptmt.execute();
		ptmt.close();
		conn.close();
	}
	
	public List<Teacher> multiquery() throws SQLException{
		//System.out.println("--------------");
		//System.out.println("��dao�ļ�");
		Connection conn=DbUtil.getConnection();
		//Statement statement = conn.createStatement();
        String sql="select  * from teacher ";
        //ResultSet set = statement.executeQuery(sql);//ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ResultSet rs=ptmt.executeQuery();
        List<Teacher> tch = new ArrayList<Teacher>();
        Teacher t=null;
        while (rs.next()){
        	t=new Teacher();
        	t.setT_name(rs.getString("name"));
        	t.setT_password(rs.getString("password"));
        	tch.add(t);
            //System.out.println(set.getString("name") + set.getString("password"));
        }
		ptmt.close();
		conn.close();
        //System.out.println("--------------");
		return tch;
		
	}
	public Teacher query(String name) throws SQLException {
		System.out.println("����query����");
		System.out.println(name);
		Teacher t = new Teacher();
		Connection conn=DbUtil.getConnection();
		System.out.println("���ݿ����ӳɹ�");
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
		ptmt.close();
		conn.close();
		return t;
	}
	
}
