package service;

import java.util.Vector;

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
}
