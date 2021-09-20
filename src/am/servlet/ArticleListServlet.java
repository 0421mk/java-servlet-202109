package am.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import am.util.SecSql;

import am.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html charset=UTF-8");
		String url = "jdbc:mysql://localhost:3306/am?ServerTimeZone=UTC";
		String user = "root";
		String password = "";

		// 커넥터 드라이버 활성화
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

			int page = 1;

			if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				try {
					page = Integer.parseInt(request.getParameter("page"));
				} catch (NumberFormatException e) {

				}
			}
			
			int itemsInAPage = 20;
			int limitFrom = (page - 1) * itemsInAPage;

			DBUtil dbUtil = new DBUtil(request, response);

			SecSql sql = new SecSql();
			
			sql.append("SELECT COUNT(*) AS cnt FROM article");
			int totalCount = dbUtil.selectRowIntValue(conn, sql);

			sql = SecSql.from("SELECT *");
			sql.append("from article");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

			List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);
			
			int totalPage = (int)Math.ceil((double)totalCount/itemsInAPage);
			
			request.setAttribute("articleRows", articleRows);
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
