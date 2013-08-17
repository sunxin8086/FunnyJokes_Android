package com.xin.funnyjokes.models;


public class Comment extends BaseObject {
   	private String jokeId;
   	private String authorId;
   	private String content;
   	
	public String getJokeId() {
		return jokeId;
	}
	public void setJokeId(String jokeId) {
		this.jokeId = jokeId;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
