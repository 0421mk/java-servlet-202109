package am.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import am.util.DBUtil;
import am.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ArticleController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
	}

	public void actionList() throws ServletException, IOException {

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

		int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);

		request.setAttribute("articleRows", articleRows);
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
		
	}

}
