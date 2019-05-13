package entity;

public class Task {
	private int t_id;
	private String t_title;
	private String t_description;
	private String t_kind;
	
	public Task() {
		
	}
	public Task(int t_id,String t_title,String t_description,String t_kind) {
		this.t_id=t_id;
		this.t_title=t_title;
		this.t_description=t_description;
		this.t_kind=t_kind;
	}
	public String toString() {
		return "Task[id="+t_id+",title="+t_title+",description="+t_description+"t_kind"+t_kind+"]";
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
	public String getT_kind() {
		return t_kind;
	}
	public void setT_kind(String t_kind) {
		this.t_kind = t_kind;
	}

	
}
