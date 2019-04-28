package action;

import java.util.ArrayList;
import java.util.List;

import dao.Student_Dao;
import dao.Task_Dao;
import entity.Student;
import entity.Task;

public class TaskAction {
	public static void main(String[] args) throws Exception{
		Task_Dao t = new Task_Dao();
		Task t1 =  new Task();
		List<Task> task = new ArrayList<Task>();
		/*task = t.query();
		for(Task t2:task) {
			System.out.println(t2.toString());
		}*/
		Student s = new Student();
		Student_Dao s_dao = new Student_Dao();
		int id = 4443;
		int res = s_dao.CheckLogByStu(id);
		System.out.println("res是："+res);
		//int id = 0;
		//id = t.SearchLastNum();
		//System.out.println(id);
		//t1.setT_password("jly");
		//t1=t.query("金璐瑶");
		//System.out.println(t1.toString());
		//t.addTeacher(t1);
		System.out.println("执行结束");
	}
}
