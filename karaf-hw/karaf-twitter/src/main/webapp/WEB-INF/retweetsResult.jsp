<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>>Karaf/Twitter assignment</title>
</head>
<body>
  	<jsp:include page="topHeader.jsp"></jsp:include>
  	
  	<h3>Retweets API</h3>
  	
  		
	<%	String[][] retweets = ((String[][])request.getAttribute("retweets"));
		String output = "";%>
	<%	if (retweets == null || retweets.length == 0) {
			output = "No tweets found";
		} else {
			for(String[] retweet : retweets) {
				output += retweet[0] + ":<br>&nbsp;&nbsp;"  + "Created On: " + retweet[1] + "<br><br>";
			}
		} %>
	<%= output %>  		
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>