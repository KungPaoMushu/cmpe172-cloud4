package twitterPackage;

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

public class RetweetsAPIServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RetweetsAPIServelt() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String[][] result = TwitterUtils.getRetweets();
    	request.setAttribute("retweets", result);
		request.getServletContext().getRequestDispatcher("/WEB-INF/retweetsResult.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
