package am.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import am.Config;
import am.util.DBUtil;
import am.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
		
		// 커넥터 드라이버 활성화
		String driverName = Config.getDriverClassName();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBId(), Config.getDBPw());

			DBUtil dbUtil = new DBUtil(request, response);
			
			int id = Integer.parseInt(request.getParameter("id"));

			SecSql sql = new SecSql();

			sql.append("SELECT * FROM article WHERE id = ?", id);
			Map<String, Object> articleRow = dbUtil.selectRow(conn, sql);
			
			request.setAttribute("articleRow", articleRow);
			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);

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
