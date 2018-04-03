<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trending Tweets Topic</title>
</head>
<body>
	<jsp:include page="topHeader.jsp"></jsp:include>
	<h3>Trending Topic On Twitter</h3>
	<% String[][] results = ((String[][])request.getAttribute("result")); 
	   String output = ""; %>
	<% if (results.length == 0) {
		   output = "No results";
	   }else {
		   output += "Trending Topic as of: " + results[0][0];
		   output += "<ol>";
		   for(String[] a : results) {
			   output += "<li>" + a[2] + " <a href=" + a[3] + ">View More</a>";
			}
		   output += "</ol>";
	   }%>
	<%= output %>
</body>
</html>