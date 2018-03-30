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
			URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="
					+ username + "&count=" + count); 
			connection = (HttpsURLConnection) url.openConnection();           
			connection.setDoOutput(true);
			connection.setDoInput(true); 
			connection.setRequestMethod("GET"); 
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "Karaf-twitter");
			connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
			connection.setUseCaches(false);
			
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
