<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  	<jsp:include page="topHeader.jsp"></jsp:include>
  	
  	<h3>followers API</h3>
  	
  	
	<%	String[][] followers = ((String[][])request.getAttribute("follower"));
		String output = "";%>
	<%	if (followers == null || followers.length == 0) {
			output = "No followers";
		} else {
			for(String[] follower : followers) {
				output += "Name: " +  follower[0] + " Screen_name: " + follower[1] + "<br><br>";
			}
		} %>
	<%= output %>  	
  	

</body>
</html>