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
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ArticleListServlet
 */
@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {

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

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM member");
			sql.append("WHERE loginId = ?", loginId);
			
			Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);
						
			if (memberRow.isEmpty()) {
				response.getWriter()
				.append(String.format("<script>alert('%s(은)는 존재하지않는 아이디입니다.'); history.back(); </script>", loginId));
				return;
			}
	
			
 			if (memberRow.get("loginPw").equals(loginPw) == false) {
				response.getWriter()
				.append(String.format("<script>alert('비밀번호가 일치하지 않습니다.'); history.back(); </script>"));
				return;
			}
 			
			HttpSession session = request.getSession();
			session.setAttribute("loginedMemberId", memberRow.get("id"));
			
			response.getWriter()
			.append("<script>alert('로그인 성공'); location.replace('../home/main'); </script>");

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
