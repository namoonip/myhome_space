package todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import util.ConnectionUtil;


public class TodoDAO {
	
	
	public ArrayList<TodoVO> getAllTodoList() throws SQLException{
		ArrayList<TodoVO> todoList = new ArrayList<>();
		String sql = "select * from tb_todo ";		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
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
			
			todoList.add(todo);
		}		
		rs.close();
		pstmt.close();
		con.close();
		
		return todoList;		
	}
	
	
	public void addTodo(TodoVO todo) throws SQLException{
		String sql="insert into tb_todo(no, category, title, description, location, day, completed) "
				+ " values(comm_seq.nextval, ?, ?, ?, ?, ?, 'no') ";
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, todo.getCategory());
		pstmt.setString(2, todo.getTitle());
		pstmt.setString(3, todo.getDescription());
		pstmt.setString(4, todo.getLocation());
		pstmt.setString(5, todo.getDay());
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
	}
	
	
	public void deleteTodoByNo(int no) throws SQLException {
		String sql = "delete from tb_todo where no = ? ";
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();		
	}
	
	public TodoVO getTodoVOByNo(int no) throws SQLException{
		TodoVO vo = new TodoVO();
		
		String sql= "select * from tb_todo where no = ? ";
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			vo.setNo(rs.getInt("no"));
			vo.setCategory(rs.getString("category"));
			vo.setTitle(rs.getString("title"));
			vo.setDescription(rs.getString("description"));
			vo.setLocation(rs.getString("location"));
			vo.setDay(rs.getString("day"));
			vo.setCompleted(rs.getString("completed"));
		}
		rs.close();
		pstmt.close();
		con.close();
		
		return vo;
	}
	
	public void completeTodo(int no) throws SQLException {
		
		String sql = "update tb_todo "
				+ " set completed = 'yes' "
				+ "	where no = ? ";
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);		
		
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
