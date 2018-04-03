/**
 * Author: Ibrahim Ibrahim
 * Get search recent tweets
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchtweets")
public class SearchTweetsAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchTweetsAPIServlet() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String enteredTweet = request.getParameter("tweet");
    	String[][] result = TwitterUtils.getSearchedTweets(enteredTweet);
    	request.setAttribute("searchedTweets", result);
		request.getServletContext().getRequestDispatcher("/WEB-INF/tweetSearchResult.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
