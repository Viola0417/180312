package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Log_Dao;
import entity.Log;

public class try_log {
	public static void main(String[] args) throws SQLException {
		Log l = new Log();
		Log_Dao l_dao = new Log_Dao();
		int res = l_dao.CheckLogByTask(2);
		//System.out.println(res);
		int res_1 = l_dao.CheckDisLogByTask(2);
		//System.out.println(res_1);
		List<Double> F_list = new ArrayList<Double>();
		List<Log> log_list = new ArrayList<Log>();
		int task_id = 53;
		int log_id = 15;
		F_list = l_dao.QueryFbyTask(task_id,log_id);
		log_list = l_dao.QueryLogByTask(task_id, log_id);
		//System.out.println(log_list);
		l = l_dao.CheckLastLog();
		System.out.println(l.toString());
	}
}
