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

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {

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

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String name = request.getParameter("name");

			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM member");
			sql.append("WHERE loginId = ?", loginId);
			
			boolean isJoinAvailableLoginId = DBUtil.selectRowIntValue(conn, sql) == 0;
			
			if (isJoinAvailableLoginId==false) {
				response.getWriter()
				.append(String.format("<script>alert('%s (은)는 이미 사용중인 아이디입니다.'); history.back(); </script>", loginId));
				return;
			}

			sql.append("INSERT INTO member");
			sql.append("SET regDate = NOW()");
			sql.append(", loginId = ?", loginId);
			sql.append(", loginPw = ?", loginPw);
			sql.append(", name = ?", name);

			int id = dbUtil.insert(conn, sql);
			response.getWriter()
					.append(String.format("<script>alert('%d번 회원이 생성되었습니다.'); location.replace('../home/main')</script>", id));

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
