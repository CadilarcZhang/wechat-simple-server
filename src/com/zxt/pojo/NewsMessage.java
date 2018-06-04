package com.zxt.pojo;

import java.util.List;

/**
 * 图文消息实体（可包含多个图文）
 * @author Administrator
 *
 */
public class NewsMessage extends BaseMessage {
	private int ArticleCount;
	private List<NewsItem> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<NewsItem> getArticles() {
		return Articles;
	}
	public void setArticles(List<NewsItem> articles) {
		Articles = articles;
	}
	
}
