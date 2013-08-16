package com.xin.funnyjokes.models;

public class Like {
   	private String jokeId;
   	private String authorId;
   	private Boolean liked;
   	
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
	public Boolean getLiked() {
		return liked;
	}
	public void setLiked(Boolean liked) {
		this.liked = liked;
	}
}
