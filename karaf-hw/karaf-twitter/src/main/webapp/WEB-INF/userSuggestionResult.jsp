<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Suggestion List</title>
</head>
<body>
	<jsp:include page="topHeader.jsp"></jsp:include>
	<h3>Top Twitter's Users Suggestion</h3>
	<% String[][] results = ((String[][])request.getAttribute("result"));
	   String printOut = "";
	   if (results != null || results.length != 0) {
		   printOut += "<table border='2' cellspacing='2'>";
		   printOut += "<tr>";
		   printOut += "<th>Username</th><th>Profile Picture</th><th># Followers</th></tr>";
		   for(String[] a : results) {
			   printOut += "<tr><td>" + a[0] + "</td>";
			   printOut += "<td><img src=\"" + a[1] + "\" style=\"align:center\"</td>";
			   printOut += "<td>" + a[2] + "</td>";
			   printOut += "</tr>";
		   }
	   } else {
		   printOut += "No results";
	   }
	   printOut += "</table>";
	   %>
	    
	<%= printOut %>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>