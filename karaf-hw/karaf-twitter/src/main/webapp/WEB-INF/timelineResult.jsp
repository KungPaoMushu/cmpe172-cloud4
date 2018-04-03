<!-- Author: Quang Duy Luong -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Karaf/Twitter assignment</title>
</head>
<body>
	<jsp:include page="topHeader.jsp"></jsp:include>

	<h3>Timeline API</h3>
	<%	String[][] tweets = ((String[][])request.getAttribute("tweets"));
		String output = "";%>
	<%	if (tweets == null || tweets.length == 0) {
			output = "No tweets found";
		} else {
			for(String[] tweet : tweets) {
				output += tweet[0] + " said on " + tweet[1] + ":<br>&nbsp;&nbsp;" + tweet[2] + "<br><br>";
			}
		} %>
	<%= output %>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
