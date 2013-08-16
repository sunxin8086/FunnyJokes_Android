package com.xin.funnyjokes.models;


public class Joke extends BaseObject{
   	private String authorId;
   	private String category;
   	private String language;
   	private String title;
   	private String content;
   	private Integer commentsNumber;
   	private Integer likesNumber;
   	private Integer dislikesNumber;
   	private Integer sharesNumber;
   	
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCommentsNumber() {
		return commentsNumber;
	}
	public void setCommentsNumber(Integer commentsNumber) {
		this.commentsNumber = commentsNumber;
	}
	public Integer getLikesNumber() {
		return likesNumber;
	}
	public void setLikesNumber(Integer likesNumber) {
		this.likesNumber = likesNumber;
	}
	public Integer getDislikesNumber() {
		return dislikesNumber;
	}
	public void setDislikesNumber(Integer dislikesNumber) {
		this.dislikesNumber = dislikesNumber;
	}
	public Integer getSharesNumber() {
		return sharesNumber;
	}
	public void setSharesNumber(Integer sharesNumber) {
		this.sharesNumber = sharesNumber;
	}

}
