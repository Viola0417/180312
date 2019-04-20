package entity;

public class Task {
	private int t_id;
	private String t_title;
	private String t_description;
	
	public Task() {
		
	}
	public Task(int t_id,String t_title,String t_description) {
		this.t_id=t_id;
		this.t_title=t_title;
		this.t_description=t_description;
	}
	public String toString() {
		return "Task[id="+t_id+",title="+t_title+",description="+t_description+"]";
	}
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_title() {
		return t_title;
	}
	public void setT_title(String t_title) {
		this.t_title = t_title;
	}
	public String getT_description() {
		return t_description;
	}
	public void setT_description(String t_description) {
		this.t_description = t_description;
	}	
	
}
