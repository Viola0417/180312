package action;

import java.util.ArrayList;
import java.util.List;

import dao.Log_Dao;
import entity.Log;

public class LogAction {
	public static void main(String[] args) throws Exception{
		Log_Dao l_dao = new Log_Dao();
		Log l = new Log();
		List<Log> log_list = new ArrayList<Log>();
		/*��Ӽ�¼����
		l.setStu_id(123456);
		l.setTask_id(2);
		l.setF(0.878);
		l.setP(0.123);
		l.setR(0.123);
		l.setDescription("�ڶ��γ���");
		l_dao.addLog(l);
		*/
		
		//���Ҽ�¼����
		log_list = l_dao.query();
		for(Log l1:log_list) {
			System.out.println(l1.toString());
		}
		System.out.println("ִ�н���");
	}
}
