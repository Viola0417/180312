package dao;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
//import entity.Teacher;
import entity.Teacher;

public class Student_Dao {
	public void addStudent(Student s) throws SQLException {
		try {
			Connection conn=DbUtil.getConnection();
			String sql=""+"insert into student"+"(name,id,password)"+"values("+"?,?,?)";
		//��sql�����ص��������򣬵���ִ��
			PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
			ptmt.setString(1, s.getS_name());
			ptmt.setInt(2, s.getS_id());
			ptmt.setString(3, s.getS_password());
		//ִ��sql���
			ptmt.execute();
		}catch(Exception e) {
			System.out.println("�������Ѿ�������");
		}
	}
	
	public void updateStudent(Student s) throws SQLException {
		//����ѧ��ѧ���޸�ѧ������
		Connection conn=DbUtil.getConnection();
		String sql=""+"update student "+"set password=? "+" where id=?";
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		//ptmt.setString(1, t.getT_name());
		ptmt.setString(1, s.getS_password());
		ptmt.setInt(2, s.getS_id());
		//ִ��sql���
		ptmt.execute();
	}
	public void delStudent(int id) throws SQLException {
		Connection conn=DbUtil.getConnection();
		String sql=""+"delete from student "+" where id=?";
		//��sql�����ص��������򣬵���ִ��
		PreparedStatement ptmt=conn.prepareStatement(sql);
		//��sql��䴫�ݲ���(��entity��̬����)
		ptmt.setInt(1,id);
		//ִ��sql���
		ptmt.execute();
	}
	
	public List<Student> multiquery() throws SQLException{
		//System.out.println("--------------");
		//System.out.println("��dao�ļ�");
		Connection conn=DbUtil.getConnection();
		Statement statement = conn.createStatement();
        String sql="select  * from student ";
        ResultSet set = statement.executeQuery(sql);//ִ��
        
        List<Student> stu = new ArrayList<Student>();
        Student s=null;
        while (set.next()){
        	s=new Student();
        	s.setS_name(set.getString("name"));
        	s.setS_id(set.getInt("id"));
        	s.setS_password(set.getString("password"));
        	stu.add(s);
            //System.out.println(set.getString("name") + set.getString("password"));
        }
        //System.out.println("--------------");
		return stu;
		
	}
	public Student query(int id) throws SQLException {
		System.out.println("����query����");
		System.out.println(id);
		Student s = new Student();
		Connection conn=DbUtil.getConnection();
		System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from student "+" where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		//System.out.println(rs);
		while(rs.next()) {
			s=new Student();
			s.setS_name(rs.getString("name"));
			s.setS_id(rs.getInt("id"));
			s.setS_password(rs.getString("password"));
			//System.out.println(t.getT_password());
		}
		
		
		return s;
	}
	
	public List selectOne(Student s) throws SQLException {
		System.out.println("�����жϺ���");
		List list = new ArrayList();
		//System.out.println(id);
		int id = s.getS_id();
		//Student s = new Student();
		Connection conn=DbUtil.getConnection();
		System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from student "+" where id=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs=ptmt.executeQuery();
		//System.out.println(rs);
		while(rs.next()) {
			if(rs.getString("id").equals(s.getS_id())) {
				list.add(1);
			}
			else {
				list.add(0);
			}
		}
		return list;
	}
	
	public Student queryByName(String name) throws SQLException {
		System.out.println("����query����");
		System.out.println(name);
		Student s = new Student();
		Connection conn=DbUtil.getConnection();
		System.out.println("���ݿ����ӳɹ�");
		String sql=""+"select * from student "+" where name=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, name);
		ResultSet rs=ptmt.executeQuery();
		//System.out.println(rs);
		while(rs.next()) {
			s=new Student();
			s.setS_name(rs.getString("name"));
			s.setS_password(rs.getString("password"));
			s.setS_id(rs.getInt("id"));
			//System.out.println(t.getT_password());
		}
		return s;
	}
	
	public int CheckLogByStu(int id) throws SQLException {
		int res = 0;
		System.out.println("����ѧ���жϸ�ͬѧ�Ƿ����");
		Connection conn=DbUtil.getConnection();
		String sql="select COUNT(*) from student where id="+"?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,id);
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()) {
			res = rs.getInt(1);
		}
		//System.out.println("select count����ǣ�"+res);
		return res;
	}
}

