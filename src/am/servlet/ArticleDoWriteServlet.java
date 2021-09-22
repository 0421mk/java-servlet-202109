package am.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import am.Config;
import am.util.DBUtil;
import am.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

			String title = request.getParameter("title");
			String body = request.getParameter("body");
			
			HttpSession session = request.getSession();

			if ( session.getAttribute("loginedMemberId") == null ) {
				response.getWriter()
				.append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../member/login'); </script>"));
				return;
			}
			
			int loginedMemberId = (int) session.getAttribute("loginedMemberId");

			SecSql sql = new SecSql();

			sql.append("INSERT INTO article");
			sql.append("SET regDate = NOW()");
			sql.append(", memberId = ?", loginedMemberId);
			sql.append(", title = ?", title);
			sql.append(", body = ?", body);

			int id = dbUtil.insert(conn, sql);
			response.getWriter()
					.append(String.format("<script>alert('%d번 글이 생성되었습니다.'); location.replace('list')</script>", id));

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
