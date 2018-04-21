package twitterPackage;
/**
 * Author: Ibrahim Ibrahim
 * Get followers
 */


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FollowersAPIServlet  extends HttpServlet {
	
	public FollowersAPIServlet(){
		super();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	String username = request.getParameter("username");
    	String[][] result = TwitterUtils.getFollowers(username);
    	request.setAttribute("follower", result);
		request.getServletContext().getRequestDispatcher("/WEB-INF/followersResult.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
