package entity;

public class F_stu_id {
	private double F;
	private int stu_id;
	private int no;
	
	public F_stu_id() {
		super();
	}
	
	public String toString() {
		return "F_stu_id:[F="+F+",stu_id="+stu_id+",NO.="+no+"]";
	}

	public double getF() {
		return F;
	}

	public void setF(double f) {
		F = f;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	

	
	
}
