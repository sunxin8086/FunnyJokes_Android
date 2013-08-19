package com.xin.funnyjokes.services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xin.funnyjokes.models.Joke;
import com.xin.funnyjokes.utils.FunnyJokesMapper;

public class FunnyJokesRestDataProvider implements FunnyJokesDataProvider {

	private static final String BASE_URL = "http://198.199.100.186:8888/xinsun/v1/";
	private static final String CATEGORIES_URL = "categories/";
	private static final String PEOPLE_URL = "people/";
	private static final String JOKES_URL = "jokes/";
	private static final String COMMENTS_URL = "comments/";
	private static final String LIKES_URL = "likes/";
	private static final String JOKES_FILTER__CATEGORY_DATE_RANGE = "{\"category\":\"%s\" , \"created\" : { \"$gte\" : \"%s\", \"$lt\" : \"%s\"}}";
	private static final String JOKES_FILTER__CATEGORY_DATE = "{\"category\":\"%s\" , \"created\" : {\"$lt\" : \"%s\"}}";
	private static final String JOKES_FILTER_CATGORY = "{\"category\":\"%s\"}";
	private static final String SORT_LIKES_NUMBER = "[(\"likes_number\", -1)]";
	private static final String SORT_CREATED = "[(\"created\", -1)]";
			
	

	private static final String TAG = "FunnyJokesRestDataProvider";

	private AsyncHttpClient client;

	public static FunnyJokesRestDataProvider instance = new FunnyJokesRestDataProvider();

	private FunnyJokesRestDataProvider() {
		this.client = new AsyncHttpClient();
		this.client.addHeader("Accept", "application/json");
	}
	
	@Override
	public void getJokes(String category, int page, FunnyJokesResponseHandler<List<Joke>> handler) {
		this.getJokes(category, 0, page, handler);
	}

	@Override
	public void getJokes(String category, int days, int page, FunnyJokesResponseHandler<List<Joke>> handler) {
		JokeSearchQuery query = new JokeSearchQuery();
		query.setCategory(category);
		query.setDays(days);
		this.getJokes(query, page, handler);
	}

	@Override
	public void getJokes(JokeSearchQuery query, int page, final FunnyJokesResponseHandler<List<Joke>> handler) {	
		this.getJokes(query.getWhere(), query.getSort(), page, handler);
	}
	
	private void getJokes(String where, String sort, int page, final FunnyJokesResponseHandler<List<Joke>> handler)
	{
		RequestParams params = new RequestParams();
		params.put("where", where);
		params.put("sort", sort);
		params.put("page", String.valueOf(page));
		

		client.get(getAbsoluteUrl(JOKES_URL), params, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject response) {
				try {
					JSONArray jokeArray = response.getJSONArray("_items");
					List<Joke> jokes = FunnyJokesMapper.mapJokes(jokeArray);
					handler.onSuccess(jokes);
				} catch (JSONException e) {
					Log.e(TAG, "Load jokes from server failed with exception " + e.toString());
				}
			}

			@Override
			public void onFailure(Throwable e, String response) {
				Log.e(TAG, "Load jokes from server failed with exception " + e.toString());
				handler.onFailure(e);
			}
		});
	}
	
	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}


}
