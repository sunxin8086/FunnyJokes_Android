package com.xin.funnyjokes.models;

import java.util.Date;

public class Person {

   	private String username;
   	private String password;
   	private String accountType;
	private Boolean sex;
	private String email;
   	private Date birthday;
   	private String profileTitle;
   	private String profileContent;
   	private Integer jokesNumber;
   	private Integer commentsNumber;
   	private Integer likesNumber;
   	private Integer dislikesNumber;
   	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getProfileTitle() {
		return profileTitle;
	}
	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}
	public String getProfileContent() {
		return profileContent;
	}
	public void setProfileContent(String profileContent) {
		this.profileContent = profileContent;
	}
	public Integer getJokesNumber() {
		return jokesNumber;
	}
	public void setJokesNumber(Integer jokesNumber) {
		this.jokesNumber = jokesNumber;
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
}
