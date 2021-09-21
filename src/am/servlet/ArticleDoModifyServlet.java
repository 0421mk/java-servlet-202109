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
@WebServlet("/article/doModify")
public class ArticleDoModifyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
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

			DBUtil dbUtil = new DBUtil(request, response);

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int id = Integer.parseInt(request.getParameter("id"));

			SecSql sql = new SecSql();

			sql.append("UPDATE article");
			sql.append("SET title = ?", title);
			sql.append(", body = ?", body);
			sql.append("WHERE id = ?", id);
			
			DBUtil.update(conn, sql);

			response.getWriter()
					.append(String.format("<script>alert('%d번 글이 수정되었습니다.'); location.replace('detail?id=%d')</script>", id, id));

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
