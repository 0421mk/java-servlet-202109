package am;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home/printDan")
public class HomePrintDanServlet extends HttpServlet {

	public HomePrintDanServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");

		String inputedDan = request.getParameter("dan");

		if (inputedDan == null) {
			inputedDan = "1";
		}
		
		String inputedLimit = request.getParameter("limit");

		if (inputedLimit == null) {
			inputedLimit = "9";
		}
		
		
		int dan = Integer.parseInt(inputedDan);
		int limit = Integer.parseInt(inputedLimit);
		String color = request.getParameter("color");
		
		if (color == null) {
			color = "";
		}
		
		response.getWriter().append(String.format("<div style=\"color:%s;\">%d단</div>", color, dan));
		
		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style=\"color:%s;\">%d * %d = %d</div>", color, dan, i, dan * i));
		}
	}

}
