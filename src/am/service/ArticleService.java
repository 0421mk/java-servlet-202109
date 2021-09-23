package am.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import am.util.DBUtil;
import am.util.SecSql;

public class ArticleService {
	private Connection conn;

	public ArticleService(Connection conn) {
		this.conn = conn;
	}

	public int getListTotalPage(int itemsInAPage) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt FROM article");
		int totalCount = DBUtil.selectRowIntValue(conn, sql);
		int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);

		return totalPage;
	}

	public List<Map<String, Object>> getArticleRowsForPrint(int page, int itemsInAPage) {

		int limitFrom = (page - 1) * itemsInAPage;

		SecSql sql = new SecSql();

		sql = SecSql.from("SELECT *");
		sql.append("from article");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

		return articleRows;
	}
}
