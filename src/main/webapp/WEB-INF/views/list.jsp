<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>list</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>no</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="board" items="${boardList}" varStatus="status">
			<tr>
				<td>${board.seq}</td>
				<td>
					<a href="/sample2/read/${board.seq}">${board.title}</a>
				</td>
				<td>${board.writer}</td>
				<td>${board.regDate}</td>
				<td>${board.count}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="/sample2">home</a>
	<a href="/sample2/write">write</a>
</body>
</html>