package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Log_Dao;
import dao.Student_Dao;
import entity.Log;

public class try_log {
	public static void main(String[] args) throws SQLException {
		Log l = new Log();
		Log_Dao l_dao = new Log_Dao();
		Student_Dao s_dao = new Student_Dao();
		int res = l_dao.CheckLogByTask(2);
		//System.out.println(res);
		int res_1 = l_dao.CheckDisLogByTask(2);
		//System.out.println(res_1);
		List<Double> F_list = new ArrayList<Double>();
		List<Log> log_list = new ArrayList<Log>();
		int task_id = 53;
		int log_id = 15;
		int stu_id = 444;
		l = new Log();
		log_list=l_dao.QueryMaxF(66);
		for(Log l1:log_list) {
			System.out.println(l1.toString());
		}
		//System.out.println(F);
		//s_dao.delStudent(stu_id);
		//l_dao.delLog(stu_id);
		System.out.println("Ö´ÐÐ½áÊø");
		////F_list = l_dao.QueryFbyTask(task_id,log_id);
		//log_list = l_dao.QueryLogByTask(task_id, log_id);
		//System.out.println(log_list);
		//l = l_dao.CheckLastLog();
		//System.out.println(l.toString());
	}
}

