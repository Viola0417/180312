package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dao.Log_Dao;
import entity.Log;

public class calculation {
	//��ȷ��=��ȷʶ����ĸ���/ʶ������ܸ���
	public double Cal_precision(Vector standard_v,Vector input_v) {
		double res = 0;
		int input_num = input_v.size();//ʶ������ܸ���
		int correct_num = 0;//���ڼ�¼��ȷʶ����ĸ���
		for(int i=0;i<input_num;i++) {
			if(standard_v.contains(input_v.get(i))) {
				//System.out.println(input_v.get(i)+"�ڲ��Լ���");
				correct_num++;
			}
		}
		//System.out.println(correct_num);
		res = (double)correct_num / (double)input_num;
		//System.out.println(res);
		return res;
	}
	
	//�ٻ���=��ȷʶ����ĸ���/���Լ������ĸ���
	public double Cal_recall(Vector standard_v,Vector input_v) {
		double res = 0;
		int input_num = input_v.size();//ʶ������ܸ���
		int standard_num = standard_v.size();//���Լ����ܸ���
		
		int correct_num = 0;//���ڼ�¼��ȷʶ����ĸ���
		for(int i=0;i<input_num;i++) {
			if(standard_v.contains(input_v.get(i))) {
				System.out.println(input_v.get(i)+"�ڲ��Լ���");
				correct_num++;
			}
		}
		System.out.println(correct_num);
		res = (double)correct_num / (double)standard_num;
		System.out.println("res��:"+res);
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
		System.out.println("�����м�¼��������ǣ�"+rank);
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
		System.out.println("��������������Ϊ"+rank);
		return rank;
	}
}
