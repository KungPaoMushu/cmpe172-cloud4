<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Karaf/Twitter assignment</title>
</head>
<body>
  	<jsp:include page="topHeader.jsp"></jsp:include>
  	
  	<h3>tweet-search API</h3>
  	
  		
	<%	String[][] tweets = ((String[][])request.getAttribute("searchedTweets"));
		String output = "";%>
	<%	if (tweets == null || tweets.length == 0) {
			output = "No tweets exist for this user";
		} else {
			for(String[] tweet : tweets) {
				output += tweet[0] + " created on " + tweet[1] + "<br><br>";
			}
		} %>
	<%= output %>  		
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

<!-- 	String tweetsStr = request.getAttribute("searchTweets").toString(); -->