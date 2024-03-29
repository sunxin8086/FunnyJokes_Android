package com.xin.funnyjokes.services;

import java.util.List;

import com.xin.funnyjokes.models.Joke;

public class FunnyJokesSmartDataProvider implements FunnyJokesDataProvider {

	public void getJokes(String category, int days, int page, FunnyJokesResponseHandler<List<Joke>> handler) {		
		FunnyJokesRestDataProvider provider = FunnyJokesRestDataProvider.instance;
		provider.getJokes(category, days, page, handler);
	}

	@Override
	public void getJokes(String category, int page, FunnyJokesResponseHandler<List<Joke>> handler) {
		FunnyJokesRestDataProvider provider = FunnyJokesRestDataProvider.instance;
		provider.getJokes(category, page, handler);
		
	}

	@Override
	public void getJokes(JokeSearchQuery query, int page, FunnyJokesResponseHandler<List<Joke>> handler) {
		FunnyJokesRestDataProvider provider = FunnyJokesRestDataProvider.instance;
		provider.getJokes(query, page, handler);
		
	}
}
