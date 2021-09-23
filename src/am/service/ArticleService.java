package am.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import am.dao.ArticleDao;
import am.util.DBUtil;
import am.util.SecSql;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int getListTotalPage(int itemsInAPage) {
		
		articleDao.getTotalCount();
		
		int totalCount = articleDao.getTotalCount();
		int totalPage = (int) Math.ceil((double) totalCount / itemsInAPage);

		return totalPage;
	}

	public List<Map<String, Object>> getArticleRowsForPrint(int page, int itemsInAPage) {

		int limitFrom = (page - 1) * itemsInAPage;

		List<Map<String, Object>> articleRows = articleDao.getArticleRows(itemsInAPage, limitFrom);

		return articleRows;
	}
}
