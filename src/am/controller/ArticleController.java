package am.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import am.dto.Article;
import am.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArticleController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
		
		articleService = new ArticleService(conn);
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
		
		int totalPage = articleService.getListTotalPage(itemsInAPage);
		
		List<Article> articles = articleService.getArticlesForPrint(page, itemsInAPage);

		request.setAttribute("articles", articles);
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
		
	}

}
