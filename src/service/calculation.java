package service;

import java.util.Vector;

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
}
