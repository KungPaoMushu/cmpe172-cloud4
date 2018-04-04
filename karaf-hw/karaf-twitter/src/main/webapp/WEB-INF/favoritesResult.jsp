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
  	<h3> Favorites API</h3>
  	
  	
	<%	String[][] favorites = ((String[][])request.getAttribute("favorites"));
		String output = "";%>
	<%	if (favorites == null || favorites.length == 0) {
			output = "No favorites";
		} else {
			for(String[] favorite : favorites) {
				output += "Tweet: " +  favorite[0] +  ":<br>&nbsp;&nbsp;"  + "Liked on: " + favorite[1] + "<br><br>";
			}
		} %>
	<%= output %>  	
  	
  	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>