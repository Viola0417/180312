package action;

import dao.Task_Dao;
import entity.Task;

public class TaskAction {
	public static void main(String[] args) throws Exception{
		Task_Dao t = new Task_Dao();
		Task t1 =  new Task();
		int id = 0;
		id = t.SearchLastNum();
		System.out.println(id);
		//t1.setT_password("jly");
		//t1=t.query("½ðè´Ñþ");
		//System.out.println(t1.toString());
		//t.addTeacher(t1);
		System.out.println("Ö´ÐÐ½áÊø");
	}
}
