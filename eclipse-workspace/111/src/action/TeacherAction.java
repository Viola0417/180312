package action;

import java.util.List;

import dao.Teacher_Dao;
import entity.Teacher;
//���ݿ����
public class TeacherAction {
	public static void main(String[] args) throws Exception{
		Teacher_Dao t = new Teacher_Dao();
		Teacher t1 =  new Teacher();
	
		//t1.setT_password("jly");
		t1=t.query("�����");
		System.out.println(t1.toString());
		//t.addTeacher(t1);
		System.out.println("ִ�н���");
	}
}
