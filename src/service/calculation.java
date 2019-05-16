package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import dao.Log_Dao;
import entity.F_stu_id;
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
	
	//根据题号对F的分布进行统计（10%，20%，30%，40%，50%）
	public List<Integer> classify_F(int task_id) throws SQLException {
		List<Integer> F_list = new ArrayList<Integer>();
		List<Double> log_list = new ArrayList<Double>();
		Log_Dao l_dao = new Log_Dao();
		//得到这题所有的F
		log_list = l_dao.QueryF(task_id);
		//System.out.println(log_list);
		int a = 0,b=0,c=0,d=0,e=0;//分别代表10%，20%，30%，40%，50%区间
		//根据F的值做统计
		for(double f:log_list) {
			if(f<=0.1) {
				a++;
				//break;
			}else if(f>0.1&&f<=0.2) {
				b++;
				//break;
			}else if(f>0.2&&f<=0.3) {
				c++;
				//break;
			}else if(f>0.3&&f<=0.4) {
				d++;
				//break;
			}else if(f>0.4&&f<=0.5) {
				e++;
				//break;
			}
		}
		//System.out.println("a="+a+",b="+b+",c="+c+",d="+d+",e="+e);
		F_list.add(a);
		F_list.add(b);
		F_list.add(c);
		F_list.add(d);
		F_list.add(e);
		System.out.println(F_list);
		return F_list;
	}
	
	//根据题目编号得到大家的排名
	public List<F_stu_id> calRankByTask(int task_id) throws SQLException{
		//System.out.println("过来了");
		Log_Dao l_dao = new Log_Dao();
		//List<Log> rank_log_list = new ArrayList<Log>();
		//所有做过这道题的人的学号+他们最高的F
		List<Log> log_list = new ArrayList<Log>();
		log_list = l_dao.QueryMaxF(task_id);
		/*
		for(Log l:log_list) {
			System.out.println(l.toString());
		}
		*/
		double max_f = 0;
		int max_f_stu = 0;
		//冒泡排序,把F最大的放在后面
		for(int i=0;i<(log_list.size()-1);i++) {
			for(int j=0;j<log_list.size()-1-i;j++) {
				if(log_list.get(j).getF()>log_list.get(j+1).getF()) {
					max_f = log_list.get(j).getF();
					max_f_stu = log_list.get(j).getStu_id();
					log_list.get(j).setF(log_list.get(j+1).getF());
					log_list.get(j).setStu_id(log_list.get(j+1).getStu_id());
					log_list.get(j+1).setF(max_f);
					log_list.get(j+1).setStu_id(max_f_stu);
				}
			}
			//System.out.println(log_list);
		}
	    //逆序
		F_stu_id fs = new F_stu_id();
		List<F_stu_id> fs_list = new ArrayList<F_stu_id>();
		int no = 1;//记录排名
		int no_same = 0;//记录一样的F
		for(int i=log_list.size()-1;i>0;i--) {
		    fs = new F_stu_id();
			fs.setF(log_list.get(i).getF());
			fs.setStu_id(log_list.get(i).getStu_id());
			if(i==log_list.size()-1) {
				fs.setNo(1);
				fs_list.add(fs);
				//break;
			}else if(log_list.get(i).getF()!=log_list.get(i+1).getF()) {
				no = no+no_same+1;
				no_same = 0;
				fs.setNo(no);
				fs_list.add(fs);
				//break;
			}else {
					no_same = no_same + 1;
					fs.setNo(no);
					fs_list.add(fs);
			}
			//有F值相同的情况

		}
		/*
		for(F_stu_id f:fs_list) {
			System.out.println(f.toString());
		}
		*/
		//System.out.println(log_list);
		return fs_list;
	}

		public List<String> reverse(List<String> list,int stu_id,int b){
			//int b = stu_id % 100;
			int list_len = list.size();
			int len = list_len;
			int last = list_len % b;
			//System.out.println(list_len);
			int time = 0;
			int i = 0;
			//b=6;
			List<String> new_list = new ArrayList<String>();
			while(list_len>=b) {
				for(i=time*b+b-1;i>=time*b;i--) {
					new_list.add(list.get(i));
				}
				time++;
				list_len=list_len-b;
				System.out.println(new_list);
			}
			System.out.println("i="+i);
			//逆序
			/*
			if(b!=0 && b!=1) {
				
			}*/
			//System.out.println(last);
			for(int j=len-last;j<len;j++) {
				//System.out.println(j);
				new_list.add(list.get(j));
			}
			System.out.println(new_list);
			System.out.println("执行结束");
			return new_list;
		}
}
