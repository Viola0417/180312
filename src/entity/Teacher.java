package entity;
//��ʦʵ����
public class Teacher {
	private String t_name;
	private String t_password;
	public Teacher() {
		super();
	}
	public Teacher(String tname,String tpwd) {
		super();
		t_name=tname;
		t_password=tpwd;
		
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_password() {
		return t_password;
	}
	public void setT_password(String t_password) {
		this.t_password = t_password;
	}
	public String toString() {
		return "����ʦΪ��"+t_name+"�����������ǣ�"+t_password;
	}
}
