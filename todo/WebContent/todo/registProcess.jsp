<%@page import="todo.UserTodoVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="todo.UserTodoDAO"%>
<%@page import="todo.LoginVO"%>
<%@page import="todo.TodoVO"%>
<%@page import="todo.TodoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ include file="loginCheck.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	
	TodoVO todo = new TodoVO();
	todo.setCategory(request.getParameter("category"));
	todo.setTitle(request.getParameter("title"));
	todo.setDescription(request.getParameter("description"));
	todo.setLocation(request.getParameter("location"));
	todo.setDay(request.getParameter("day"));	
	TodoDAO dao = new TodoDAO();
	dao.addTodo(todo);
	
	LoginVO loginedUser = (LoginVO) session.getAttribute("LOGINED_USER");
	UserTodoDAO userDao = new UserTodoDAO();
	userDao.addToDo(loginedUser, todo.getNo());
	
	response.sendRedirect("index.jsp");
%>