package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dao.Log_Dao;
import entity.Log;

public class calculation {
	//正确率=正确识别出的个数/识别出的总个数
	public double Cal_precision(Vector standard_v,Vector input_v) {
		double res = 0;
		int input_num = input_v.size();//识别出的总个数
		int correct_num = 0;//用于记录正确识别出的个数
		for(int i=0;i<input_num;i++) {
			if(standard_v.contains(input_v.get(i))) {
				//System.out.println(input_v.get(i)+"在测试集中");
				correct_num++;
			}
		}
		//System.out.println(correct_num);
		res = (double)correct_num / (double)input_num;
		//System.out.println(res);
		return res;
	}
	
	//召回率=正确识别出的个数/测试集给出的个数
	public double Cal_recall(Vector standard_v,Vector input_v) {
		double res = 0;
		int input_num = input_v.size();//识别出的总个数
		int standard_num = standard_v.size();//测试集的总个数
		
		int correct_num = 0;//用于记录正确识别出的个数
		for(int i=0;i<input_num;i++) {
			if(standard_v.contains(input_v.get(i))) {
				System.out.println(input_v.get(i)+"在测试集中");
				correct_num++;
			}
		}
		System.out.println(correct_num);
		res = (double)correct_num / (double)standard_num;
		System.out.println("res是:"+res);
		return res;
	}
	
	public int Cal_Log_Rank(Log l) throws SQLException {
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		System.out.println(l.toString());
		int rank = 1;
		Log_Dao l_dao = new Log_Dao();
		List<Double> F_list = new ArrayList<Double>();
		F_list = l_dao.QueryFbyTask(l.getTask_id(),l.getLog_id());
		System.out.println(F_list);
		double F = l.getF();
		for(double f:F_list) {
			if(f>F) {
				rank++;
			}
		}
		System.out.println("在所有记录里的排名是："+rank);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		return rank;
	}
	
	public int Cal_Stu_Rank(Log stu_log) throws SQLException {
		int rank = 1;
		Log_Dao l_dao = new Log_Dao();
		List<Log> log_list = new ArrayList<Log>();
		log_list = l_dao.QueryLogByTask(stu_log.getTask_id(), stu_log.getLog_id());
		List<Integer> stuid_list = new ArrayList<Integer>();
		int stu_id = stu_log.getStu_id();
		double stu_F = stu_log.getF();
		for(Log l:log_list) {
			if(l.getStu_id()!=stu_id) {
				if(l.getF()>stu_F) {
					if(!stuid_list.contains(l.getStu_id())) {
						stuid_list.add(l.getStu_id());
						rank++;
					}									
				}
			}
		}
		System.out.println(stuid_list);
		System.out.println("在所有人中排名为"+rank);
		return rank;
	}
}
