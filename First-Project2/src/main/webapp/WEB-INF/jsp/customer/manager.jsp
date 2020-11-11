<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello thymeleaf</title>

	
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css" >
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap-theme.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</head>

<style>

</style>
<body>

	<div>
		<table class="table" style="table-layout:fixed">
			<thead>
				<tr>
					<th>구분</th>
					<th>담당자</th>
					<th>연락처</th>
					<th>From</th>
					<th>to</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>
						<td>${item.code_name}</td>
						<td>${item.employee_name}</td>
						<td>${item.employee_contact}</td>
						<td>${item.managerHistory_startDate}</td>
						<td>${item.managerHistory_endDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	


</body>
</html>