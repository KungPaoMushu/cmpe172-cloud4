<!-- Author: Quang Duy Luong -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Karaf/Twitter assignment</title>
</head>
<body>
	<h1>Cloud 4</h1>
	<ul>
		<li>Cameron Chien</li>
		<li>Jie Peng Hu</li>
		<li>Ibrahim Ibrahim</li>
		<li>Quang Duy Luong</li>
	</ul>
	<br>
	<h3>Timeline API</h3>
	<%	String[][] tweets = ((String[][])request.getAttribute("tweets"));
		String output = "";%>
	<%	if (tweets == null || tweets.length == 0) {
			output = "No tweets exist for this user";
		} else {
			for(String[] tweet : tweets) {
				output += tweet[0] + " said on " + tweet[1] + ":<br>&nbsp;&nbsp;" + tweet[2] + "<br><br>";
			}
		} %>
	<%= output %>
</body>
</html>
