package entity;

import java.sql.Timestamp;

public class Log {
	private int log_id;
	private int stu_id;
	private int task_id;
	private String description;
	private String algo;
	private double F;
	private double R;
	private double P;
	private Timestamp time;
	
	public Log() {
		
	}
	
	public Log(int log_id,int stu_id,int task_id,String description,String algo,double F,double R,double P,Timestamp time) {
		this.log_id = log_id;
		this.stu_id = stu_id;
		this.task_id = task_id;
		this.description = description;
		this.algo = algo;
		this.F = F;
		this.R = R;
		this.P = P;
		this.time = time;
	}
	
	public String toString() {
		return "Log[log_id="+log_id+",stu_id="+stu_id+",task_id="+task_id+"description="+description+"algo="+algo+"F="+F+"R="+R+"P="+P+"time="+time+"]";
	}

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public double getF() {
		return F;
	}

	public void setF(double f) {
		F = f;
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	public double getP() {
		return P;
	}

	public void setP(double p) {
		P = p;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
