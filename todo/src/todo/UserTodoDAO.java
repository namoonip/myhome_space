package todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionUtil;

public class UserTodoDAO {
	
	// 로그인 후 ToDo List 불러오기
	public ArrayList<UserTodoVO> getAllToDoListById(LoginVO userid) throws SQLException{
		ArrayList<UserTodoVO> UserTodolist = new ArrayList<>();
		
		String sql = "select a.no, a.CATEGORY, a.TITLE, a.DESCRIPTION, a.LOCATION, a.DAY, a.COMPLETED "
				+ " from tb_todo a, tb_user_todo b "
				+ " where a.no = b.todo_no "
				+ " and b.USER_ID = ?";
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userid.getId());
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			TodoVO todo = new TodoVO(); 
			todo.setNo(rs.getInt("no"));
			todo.setCategory(rs.getString("category"));
			todo.setTitle(rs.getString("title"));
			todo.setDescription(rs.getString("description"));
			todo.setLocation(rs.getString("location"));
			todo.setDay(rs.getString("day"));
			todo.setCompleted(rs.getString("completed"));
			
			UserTodoVO user = new UserTodoVO();
			user.setUser(userid);
			user.setTodo(todo);
			
			UserTodolist.add(user);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return UserTodolist;		
	}
	
	
	// 로그인 후 새 일정 등록하기
	public void addToDo(LoginVO userid, int todoNo) throws SQLException{
		String sql = "insert into TB_USER_TODO( no, user_id,todo_no)"
				+ " values(todo_seq.nextval, ?, ?)";
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userid.getId());
		pstmt.setInt(2, todoNo);
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();		
	}

}
