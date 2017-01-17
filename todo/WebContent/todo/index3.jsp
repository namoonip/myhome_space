<%@page import="java.util.ArrayList"%>
<%@page import="todo.TodoDAO"%>
<%@page import="todo.TodoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ include file="loginCheck.jsp" %>
<%
	TodoDAO dao = new TodoDAO();
	ArrayList<TodoVO> todoList = dao.getAllTodoList();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap-3.3.2/css/bootstrap.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>To Do Form</title>
</head>
<body>
	<div class="navbar">
		<div class="container-fluid well">
			<div class="navbar-header">
				<a href="">Todo App</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-3">
				<form action="todoForm.jsp" method="post">
					<div class="panel panel-info">
						<div class="panel-heading">일정목록</div>
						<div class="panel-body">
							<table class="table">
								<colgroup>
									<col width="*" />
									<col width="20%" />
									<col width="20%" />
									<col width="18%" />
								</colgroup>
								<thead>
									<tr>
										<th>제목</th>
										<th>날짜</th>
										<th>완료</th>
										<th></th>
									</tr>
								</thead>
								<%
									if (todoList.size() == 0) {
								%>
								<tbody>
									<tr>
										<th colspan="4" style="font-size: 30px; color: red;">등록된
											일정이 없습니다.</th>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer panel-info text-left">
							<a href="login/logoutProcess.jsp" class="btn btn-default">로그아웃</a>
							<button class="btn btn-info pull-right">새 일정 등록</button>
						</div>
						<%
							return;
							}

							for (TodoVO todo : todoList) {
						%>

						<tbody>
							<tr>
								<td><a href="detail.jsp?no=<%=todo.getNo()%>"><%=todo.getTitle()%></a></td>
								<td><a href="detail.jsp?no=<%=todo.getNo()%>"><%=todo.getDay()%></a></td>
								<td><a href="detail.jsp?no=<%=todo.getNo()%>"><%=todo.getCompleted()%></a></td>
								<%
									if (todo.getCompleted().equals("yes")) {
								%>
								<td>
									<button class="btn btn-success btn-sm" disabled>완료</button> <%
 	} else {
 %>
								
								<td><a href="completeProcess.jsp?no=<%=todo.getNo()%>"
									class="btn btn-success btn-sm">완료</a> <%
 	}
 %> <a href="deleteProcess.jsp?no=<%=todo.getNo()%>"
									class="btn btn-danger btn-sm">취소</a></td>
							</tr>
						</tbody>
						<%
							}
						%>
						</table>
					</div>
					<div class="panel-footer panel-info text-left">
						<a href="login/logoutProcess.jsp" class="btn btn-default">로그아웃</a>
						<button class="btn btn-info pull-right">새 일정 등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>