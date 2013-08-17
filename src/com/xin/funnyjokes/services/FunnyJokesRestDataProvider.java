package com.xin.funnyjokes.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xin.funnyjokes.models.Joke;
import com.xin.funnyjokes.utils.FunnyJokesUtils;

public class FunnyJokesRestDataProvider implements FunnyJokesDataProvider {

	private static final String BASE_URL = "http://198.199.100.186:8888/xinsun/v1/";
	private static final String CATEGORIES_URL = "categories/";
	private static final String PEOPLE_URL = "people/";
	private static final String JOKES_URL = "jokes/";
	private static final String COMMENTS_URL = "comments/";
	private static final String LIKES_URL = "likes/";

	private AsyncHttpClient client;

	public static FunnyJokesRestDataProvider instance = new FunnyJokesRestDataProvider();

	private FunnyJokesRestDataProvider() {
		this.client = new AsyncHttpClient();
		this.client.addHeader("Accept", "application/json");
	}

	@Override
	public void getJokes(String category, int page,
			 final FunnyJokesResponseHandler<List<Joke>> handler) {
		RequestParams params = new RequestParams();
		params.put("page", String.valueOf(page));

		client.get(getAbsoluteUrl(JOKES_URL), params,
				new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject response) {
						try {
							List<Joke> jokes = new ArrayList<Joke>();
							JSONArray jokeArray = response.getJSONArray("_items");
							for(int i = 0; i < jokeArray.length(); i ++)
							{
								JSONObject jokeObject = jokeArray.getJSONObject(i);
								Joke joke = new Joke();
								joke.setId(jokeObject.getString("_id"));
								joke.setAuthorId(jokeObject.getString("author_id"));
								joke.setContent(jokeObject.getString("content"));
								joke.setCreated(FunnyJokesUtils.getDateFromString(jokeObject.getString("created")));
								jokes.add(joke);
							}
							handler.onSuccess(jokes);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(response);
					}

					@Override
					public void onFailure(Throwable e, String response) {
				         handler.onFailure(e);
				     }
				});

	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}
