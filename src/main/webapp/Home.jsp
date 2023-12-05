<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style>
div {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.extra {
	
	margin-top: 20px;
	margin-left: 305px;
}
</style>
</head>
<body>
	<h1 align="center">This is Home Page</h1>
	<div>
		<table border="1">
			<tr>
				<th>Task Name</th>
				<th>Task Description</th>
				<th>Status</th>
				<th>Created Time</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>
		</table>
	</div>
	<a href="AddTask.html"><button class="extra">Add Task</button></a>
	<a href="Login.html"><button class="extra">Logout</button></a>
</body>
</html>