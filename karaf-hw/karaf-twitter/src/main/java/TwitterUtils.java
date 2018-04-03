/**
 * Author: Cloud4
 * Purpose: Centralized API implementation class to be used by servlets
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class TwitterUtils {
	/**
	 * Author: Quang Duy Luong
	 * @param username - username of Twitter user whose tweets should be retrieved
	 * @param count - number of tweets to be retrieved
	 * @return (String[][]) representation of the data retrieved
	 * @throws IOException
	 */
	public static String[][] getUserTweets(String username, int count) throws IOException {
		HttpsURLConnection connection = null;
		String bearerToken = getBearerToken();
		try {
			URL apiURl = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="
					+ username + "&count=" + count);
			connection = (HttpsURLConnection) apiURl.openConnection();  
			
			// Helper method to establish the connection with the specified method: GET/POST
			establishConnection(connection, bearerToken, "GET");
			
			//JSON parser reads extracts the necessary information from the results JSON object
			JSONArray obj = (JSONArray)JSONValue.parse(getResponse(connection));
			if (obj != null) {
				String[][] tweets = new String[obj.size()][3];
				for(int i = 0; i < tweets.length; i++) {
					tweets[i] = new String[3];
					tweets[i][2] = ((JSONObject)obj.get(i)).get("text").toString();
					tweets[i][1] = ((JSONObject)obj.get(i)).get("created_at").toString();
					tweets[i][0] = "@" + username;
				}
				return tweets;
			}
			return null;
		}
		catch (MalformedURLException e) {
			throw new IOException("URL could not be resolved.", e);
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	/**
	 * Author: ibrahim
	 * @param tweet - tweet to be searched
	 * @return - returns all mentions of tweet.
	 * @throws IOException
	 */
	public static String[][] getSearchedTweets(String tweet) throws IOException{
		HttpsURLConnection connection = null;
		String bearerToken = getBearerToken();
		try {
			URL apiURl = new URL("https://api.twitter.com/1.1/search/tweets.json?q="+ tweet);
			connection = (HttpsURLConnection) apiURl.openConnection();  
			
			// Helper method to establish the connection with the specified method: GET/POST
			establishConnection(connection, bearerToken, "GET");
			
			
		    //JSON parser reads extracts the necessary information from the results JSON object
			JSONObject obj2 = (JSONObject) JSONValue.parse(getResponse(connection));
			JSONArray obj = (JSONArray) obj2.get("statuses");
			if (obj != null) {
				String[][] tweets = new String[obj.size()][2];
				for(int i = 0; i < tweets.length; i++) {
					tweets[i] = new String[2];
					tweets[i][0] = ((JSONObject)obj.get(i)).get("text").toString();
					tweets[i][1] = ((JSONObject)obj.get(i)).get("created_at").toString();
				}
				return tweets;
			}
			return null;
		}
		catch (MalformedURLException e) {
			throw new IOException("URL could not be resolved.", e);
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	/**
	 * Author: ibrahim 
	 * @return list of followers
	 * @throws IOException
	 */
	public static String[][] getFollowers() throws IOException{
		HttpsURLConnection connection = null;
		String bearerToken = getBearerToken();
		try {
			URL apiURl = new URL("https://api.twitter.com/1.1/followers/list.json?cursor=-1&screen_name=twitterdev&skip_status=true&include_user_entities=false");
			connection = (HttpsURLConnection) apiURl.openConnection();  
			
			// Helper method to establish the connection with the specified method: GET/POST
			establishConnection(connection, bearerToken, "GET");
			
			
			JSONObject obj2 = (JSONObject) JSONValue.parse(getResponse(connection));
			JSONArray obj = (JSONArray) obj2.get("users");
			System.out.println(obj2);
			if (obj != null) {
				String[][] followers = new String[obj.size()][2];
				for(int i = 0; i < followers.length; i++) {
					followers[i] = new String[2];
					followers[i][0] = ((JSONObject)obj.get(i)).get("name").toString();
					followers[i][1] = ((JSONObject)obj.get(i)).get("screen_name").toString();
				}
				return followers;
			}
			return null;
		}
		catch (MalformedURLException e) {
			throw new IOException("URL could not be resolved.", e);
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	/**
	 * Author: Jie Peng Hu
	 * This is a helper method to establish connection with twitter
	 * @param conn Input HttpsURLConnection
	 * @param token 
	 * @param method: GET or POST
	 * @throws IOException
	 */
	private static void establishConnection(HttpsURLConnection conn, String token, String method) throws IOException {       
		conn.setDoOutput(true);
		conn.setDoInput(true); 
		conn.setRequestMethod(method); 
		conn.setRequestProperty("Host", "api.twitter.com");
		conn.setRequestProperty("User-Agent", "Karaf-twitter");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		conn.setUseCaches(false);
	}

	/**
	 * Author: Quang Duy Luong
	 * @return (String) an authentication token from Twitter API
	 * @throws IOException
	 */
	private static String getBearerToken () throws IOException {
		String credentials = Config.CONSUMER_KEY + ":" + Config.CONSUMER_SECRET;
		String encodedKey = new String(Base64.getEncoder().encode(credentials.getBytes()));
		HttpsURLConnection connection = null;
		URL url = new URL("https://api.twitter.com/oauth2/token"); 
		connection = (HttpsURLConnection) url.openConnection();           
		connection.setDoOutput(true);
		connection.setRequestMethod("POST"); 
		connection.setRequestProperty("Host", "api.twitter.com");
		connection.setRequestProperty("User-Agent", "Karaf-twitter");
		connection.setRequestProperty("Authorization", "Basic " + encodedKey);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8"); 
		connection.setRequestProperty("Content-Length", "29");
		connection.setUseCaches(false);
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
		wr.write("grant_type=client_credentials");
		wr.flush();
		wr.close();
		JSONObject obj = (JSONObject)JSONValue.parse(getResponse(connection));
		if (connection != null) {
			connection.disconnect();
		}
		if (obj != null) {
			String tokenType = (String)obj.get("token_type");
			String token = (String)obj.get("access_token");

			return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
		}
		return "";
	}

	/**
	 * Author: Quang Duy Luong
	 * @param connection - connection object to be read from
	 * @return String representation of the server's response
	 */
	private static String getResponse(HttpsURLConnection connection) {
		try {
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while((line = reader.readLine()) != null) {
				builder.append(line + System.getProperty("line.separator"));
			}
			return builder.toString();
		}
		catch (IOException e) {
			return "";
		}
	}
}
