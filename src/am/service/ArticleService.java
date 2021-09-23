package am.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import am.dao.ArticleDao;
import am.dto.Article;

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

	public List<Article> getArticlesForPrint(int page, int itemsInAPage) {

		int limitFrom = (page - 1) * itemsInAPage;

		List<Article> articles = articleDao.getArticles(itemsInAPage, limitFrom);

		return articles;
	}
}
