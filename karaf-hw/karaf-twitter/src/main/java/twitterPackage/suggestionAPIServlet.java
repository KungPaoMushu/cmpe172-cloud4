package twitterPackage;
/**
 * Author: Jie Peng Hu
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class suggestionAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public suggestionAPIServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String topic = request.getParameter("topic");
		Integer numberOfResults = Integer.parseInt(request.getParameter("resultCount"));
		String[][] twitterResponseForSlug = TwitterUtils.getUsersSuggestionList(topic, numberOfResults);
		request.setAttribute("result", twitterResponseForSlug);
		request.getServletContext().getRequestDispatcher("/WEB-INF/userSuggestionResult.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
