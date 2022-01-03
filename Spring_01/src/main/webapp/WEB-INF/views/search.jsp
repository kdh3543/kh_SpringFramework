<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<form action="searchByMultiCon" method="post">
		<input type=text name=name placeholder="search name"><br>
		<input type=text name=contact placeholder="search contact"><br>
		<button>검색</button>
	</form>
</body>
</html>