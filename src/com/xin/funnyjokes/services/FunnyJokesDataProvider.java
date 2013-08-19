package com.xin.funnyjokes.services;

import java.util.List;

import com.xin.funnyjokes.models.Joke;

public interface FunnyJokesDataProvider {
	public void getJokes(String category, int page, FunnyJokesResponseHandler<List<Joke>> handler);
	public void getJokes(String category, int days, int page, FunnyJokesResponseHandler<List<Joke>> handler);
	public void getJokes(JokeSearchQuery query, int page, final FunnyJokesResponseHandler<List<Joke>> handler);

}
