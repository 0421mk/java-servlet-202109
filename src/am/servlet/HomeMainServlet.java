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

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {
	
	@Override
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

			HttpSession session = request.getSession();
			
			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> loginedMemberRow = null;
			
			// 세션이 존재한다면 다음과 같이 처리.
			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int)session.getAttribute("loginedMemberId");
				isLogined = true;
				
				// memberRow 생성.
				SecSql sql = SecSql.from("SELECT * FROM member");
				sql.append("WHERE id = ?", loginedMemberId);
				loginedMemberRow = DBUtil.selectRow(conn, sql);
			}
			
			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMemberRow", loginedMemberRow);
			
			request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);

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
