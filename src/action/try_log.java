package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.Log_Dao;
import dao.Student_Dao;
import dao.Task_Dao;
import entity.Log;
import entity.Task;

public class try_log {
	public static void main(String[] args) throws SQLException {
		Log l = new Log();
		int task_id=90;
		String task_kind = "»Ø¹é";
		Log_Dao l_dao = new Log_Dao();
		List<String> list = l_dao.QueryAlgobyTask(task_id);
		System.out.println(list);
		System.out.println("Ö´ÐÐ½áÊø");
	}
}

