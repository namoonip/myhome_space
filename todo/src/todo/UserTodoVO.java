package todo;

import java.sql.Date;

public class UserTodoVO {
	private int no;
	private Date regdate;
	private TodoVO todo;
	private LoginVO user;
	
	public UserTodoVO() {
	}
	public UserTodoVO(int no, Date regdate, TodoVO todo, LoginVO user) {
		super();
		this.no = no;
		this.regdate = regdate;
		this.todo = todo;
		this.user = user;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public TodoVO getTodo() {
		return todo;
	}
	public void setTodo(TodoVO todo) {
		this.todo = todo;
	}
	public LoginVO getUser() {
		return user;
	}
	public void setUser(LoginVO user) {
		this.user = user;
	}
}
