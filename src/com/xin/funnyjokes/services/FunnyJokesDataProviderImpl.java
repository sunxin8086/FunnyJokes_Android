package com.xin.funnyjokes.services;

import java.util.ArrayList;
import java.util.List;

import com.xin.funnyjokes.models.Joke;

public class FunnyJokesDataProviderImpl implements FunnyJokesDataProvider {

	public void getJokes(String category, int page, FunnyJokesResponseHandler<List<Joke>> handler) {
		
		List<Joke> jokes = new ArrayList<Joke>();
		Joke joke;
		
		for(int i = 0; i < 4; i++)
		{
			joke = new Joke();
			joke.setId("" + i);
			jokes.add(joke);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.onSuccess(jokes);
	}
}
