package com.xin.funnyjokes.services;

public interface FunnyJokesResponseHandler <T> {
	public void onSuccess(T t);
	public void onFailure(Exception e);
}
