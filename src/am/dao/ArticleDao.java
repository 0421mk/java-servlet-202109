package am.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import am.dto.Article;
import am.util.DBUtil;
import am.util.SecSql;

public class ArticleDao {
	private Connection conn;
	
	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int getTotalCount() {
		
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt FROM article");
		
		return DBUtil.selectRowIntValue(conn, sql);
		
	}

	public List<Article> getArticles(int itemsInAPage, int limitFrom) {
		
		SecSql sql = new SecSql();

		sql = SecSql.from("SELECT *");
		sql.append("from article");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);
		
		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
		
		List<Article> articles = new ArrayList<>();
		
		for ( Map<String, Object> articleRow : articleRows ) {
			articles.add(new Article(articleRow));
		}
		
		return articles;
		
	}
}
