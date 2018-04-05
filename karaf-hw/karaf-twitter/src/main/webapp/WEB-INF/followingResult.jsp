<!-- Author: Cameron -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  	<h3> Following API</h3>
  	
  	
	<%	String[][] friends = ((String[][])request.getAttribute("following"));
		String output = "";%>
	<%	if (friends == null || friends.length == 0) {
			output = "No followers";
		} else {
			for(String[] following : friends) {
				output += "Name: " +  following[0] + " Screen_name: " + following[1] + "<br><br>";
			}
		} %>
	<%= output %>  	
  	
  	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>