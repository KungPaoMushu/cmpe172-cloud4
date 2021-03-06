package twitterPackage;
/**
 * Author: Cameron
 * Servlet for retrieving favorite tweets
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FavoritesAPIServlet  extends HttpServlet {
	
	public FavoritesAPIServlet(){
		super();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	String username = request.getParameter("username");
    	String[][] result = TwitterUtils.getFavorites(username);
    	request.setAttribute("favorites", result);
		request.getServletContext().getRequestDispatcher("/WEB-INF/favoritesResult.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

