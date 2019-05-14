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
		String task_kind = "�ع�";
		Log_Dao l_dao = new Log_Dao();
		Student_Dao s_dao = new Student_Dao();
		int res = l_dao.CheckLogByTask(2);
		//System.out.println(res);
		int res_1 = l_dao.CheckDisLogByTask(2);
		//System.out.println(res_1);
		List<Double> F_list = new ArrayList<Double>();
		List<Log> log_list = new ArrayList<Log>();
		List<Integer> task_id_list = new ArrayList<Integer>();
		List<Integer> res_list = new ArrayList<Integer>();
		List<String> algo_list = new ArrayList<String>();
		Task_Dao t_dao = new Task_Dao();
		task_id_list = t_dao.queryIDbyKind(task_kind);
		//System.out.println(task_id_list);
		int a_num = 0;//��¼SVM
		int b_num = 0;//��¼������
		int c_num = 0;//��¼KNN
		int d_num = 0;//��¼���ɭ��
		int e_num = 0;//��¼���ر�Ҷ˹
		int f_num = 0;//��¼�߼��ع�
		int g_num = 0;//��¼����
		for(int task_id:task_id_list) {
			//System.out.println("++++++++++++++++++++++++++++++++");
			//System.out.println(task_id);
			algo_list = l_dao.QueryAlgobyTask(task_id);
			if(!algo_list.isEmpty()) {
				for(String s:algo_list) {
					//System.out.println(s);
					if(s.equals("SVM")) {
						a_num++;
					}else if(s.equals("������")) {
						b_num++;
					}else if(s.equals("KNN�����")) {
						c_num++;
					}else if(s.equals("���ɭ��")) {
						d_num++;
					}else if(s.equals("���ر�Ҷ˹")) {
						e_num++;
					}else if(s.equals("�߼��ع�")) {
						f_num++;
					}else if(s.equals("����")) {
						g_num++;
					}
				}
	
			}
			
			//System.out.println(algo_list);
			//System.out.println("++++++++++++++++++++++++++++++++");
		}
		//System.out.println(a_num);

		res_list.add(a_num);
		res_list.add(b_num);
		res_list.add(c_num);
		res_list.add(d_num);
		res_list.add(e_num);
		res_list.add(f_num);
		res_list.add(g_num);
		//System.out.println(res_list);
	}
}

