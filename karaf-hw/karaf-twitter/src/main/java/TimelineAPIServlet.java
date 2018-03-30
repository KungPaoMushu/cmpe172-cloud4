/**
 * Author: Quang Duy Luong
 * Get Tweet Timelines API servlet
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/timelinetweets")
public class TimelineAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TimelineAPIServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		Integer count = Integer.parseInt(request.getParameter("count"));
		String[][] result = TwitterUtils.getUserTweets(user, count);
		request.setAttribute("tweets", result);
		request.getServletContext().getRequestDispatcher("/WEB-INF/timelineResult.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
