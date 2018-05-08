package twitterPackage;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * JUnit test cases file for Twitter API calls
 * Each function tests 1 of the API calls
 * Because of the nature of Twitter, no test tweets or test accounts are used as even they are
 * 	susceptible to changes due to social media activity.
 * @author Quang Duy Luong
 *
 */
public class TwitterTest {
	
	@Test
	public void testGetUserTweets() {
		String username = "POTUS44";
		int count = 15;
		String[][] result = null;
		try {
			result = TwitterUtils.getUserTweets(username, count);
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);			// response shouldn't be null
		assertEquals(count, result.length);		// should contain 15 tweets
		assertEquals("@POTUS44", result[0][0]);	// author field should be @POTUS44
	}
	
	@Test
	public void testGetSearchedTweets() {
		String tweet = "parenthood";
		String[][] result = null;
		try {
			result = TwitterUtils.getSearchedTweets(tweet);
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);				// response shouldn't be empty
		assertTrue(result.length > 1);				// should contain at least 1 tweet
		assertTrue(result[0][0].toLowerCase().contains(tweet));	// tweet should contain the keyword
	}
	
	@Test
	public void testGetFollowers() {
		String username = "POTUS";
		String[][] result = null;
		try {
			result = TwitterUtils.getFollowers(username);
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);				// response shouldn't be null
		assertTrue(result.length > 1);				// account POTUS should have at least 1 follower
	}
	
	@Test
	public void testGetFriends() {
		String username = "POTUS";
		String[][] result = null;
		try {
			result = TwitterUtils.getFriends(username);
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);				// response shouldn't be null
		assertTrue(result.length >= 1);				// account POTUS should follow at least 1 person
	}
	
	@Test
	public void testGetFavorites() {
		String username = "POTUS";
		String[][] result = null;
		try {
			result = TwitterUtils.getFavorites(username);
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);				// response shouldn't be null
		assertTrue(result.length > 1);				// account POTUS should have more than 1 like
	}
	
	@Test
	public void testGetTrendingTweets() {
		int number = 5;
		String[][] result = null;
		try {
			result = TwitterUtils.getTrendingTweets(1, number);
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);				// response shouldn't be null
		assertEquals(number, result.length);		// there should be exactly 5 results
	}
	
	@Test
	public void testGetUsersSuggestionList() {
		String topic = "Sports";
		int count = 5;
		String[][] result = null;
		try {
			result = TwitterUtils.getUsersSuggestionList(topic, count);
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);				// response shouldn't be null
		assertEquals(count, result.length);			// there should be exactly 5 results
	}

	@Test
	public void testGetRetweets() {
		String[][] result = null;
		try {
			result = TwitterUtils.getRetweets("");
		} catch (IOException e) {
			fail("Exception was thrown");
		}
		assertNotEquals(null, result);				// response shouldn't be null
		assertTrue(result.length >= 1);				// There should be more than 1 retweets
		assertTrue(result[0][0].contains("RT @TwitterDev"));	// text should contain RT and account name
	}
}
