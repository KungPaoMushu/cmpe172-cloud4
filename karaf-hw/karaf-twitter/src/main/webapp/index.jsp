<!-- Author: Jie Peng Hu -->
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
	<h3>Twitter REST APIs</h3>
	<h4>Timeline API</h4>
	<p>Retrieves the latest <b>number</b> tweets by the specified <b>Twitter username</b>.<br>Includes retweets as well. Implements Twitter's Get Tweet Timelines API: https://api.twitter.com/1.1/statuses/user_timeline.json</p>
	<form method="POST" action="timelinetweets">
		<input name="user" type="text" placeholder="Twitter username">
		<input name="count" type="number" placeholder="Number of tweets" min="1" max="200">
		<input type="submit" value="click">
	</form>
</body>
</html>
