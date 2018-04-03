

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/trendingtweets")
public class TrendingAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public TrendingAPIServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer count = Integer.parseInt(request.getParameter("count"));
		String[][] twitterResponse = TwitterUtils.getTrendingTweets(1, count);
		request.setAttribute("result", twitterResponse);
		request.getServletContext().getRequestDispatcher("/WEB-INF/trendingResult.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}