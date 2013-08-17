package com.xin.funnyjokes.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xin.funnyjokes.models.Category;
import com.xin.funnyjokes.models.Comment;
import com.xin.funnyjokes.models.Joke;
import com.xin.funnyjokes.models.Like;
import com.xin.funnyjokes.models.Person;

public class FunnyJokesMapper {
	
	public static Category mapCategory(JSONObject obj) throws JSONException
	{
		Category category = new Category();
		category.setId(obj.getString("_id"));
		category.setEtag(obj.getString("etag"));
		category.setLanguage(obj.getString("language"));
		category.setName(obj.getString("name"));
		category.setShortName(obj.getString("content"));
		category.setDescription(obj.getString("description"));
		category.setCount(obj.getInt("count"));
		category.setHidden(obj.getBoolean("hidden"));
		category.setCreated(FunnyJokesUtils.getDateFromString(obj.getString("created")));
		category.setUpdated(FunnyJokesUtils.getDateFromString(obj.getString("updated")));
		return category;
	}
	
	public static List<Category> mapCategories(JSONArray objs) throws JSONException
	{
		List<Category> categories = new ArrayList<Category>();
		for(int i = 0; i < objs.length(); i ++)
		{
			JSONObject categoryObject = objs.getJSONObject(i);
			categories.add(mapCategory(categoryObject));
		}
		return categories;
	}
	
	public static Person mapPerson(JSONObject obj) throws JSONException
	{
		Person person = new Person();
		person.setId(obj.getString("_id"));
		person.setEtag(obj.getString("etag"));
		person.setUsername(obj.getString("username"));
		person.setEmail(obj.getString("email"));
		person.setAccountType(obj.getString("account_type"));
		person.setSex(obj.getBoolean("sex"));
		person.setBirthday(FunnyJokesUtils.getDateFromString(obj.getString("birthday")));
		person.setProfileTitle(obj.getString("profile_title"));
		person.setProfileContent(obj.getString("profile_content"));
		person.setJokesNumber(obj.getInt("jokes_number"));
		person.setCommentsNumber(obj.getInt("comments_number"));
		person.setLikesNumber(obj.getInt("likes_number"));
		person.setDislikesNumber(obj.getInt("dislikes_number"));
		person.setCreated(FunnyJokesUtils.getDateFromString(obj.getString("created")));
		person.setUpdated(FunnyJokesUtils.getDateFromString(obj.getString("updated")));
		return person;
	}
	
	public static List<Person> mapPeople(JSONArray objs) throws JSONException
	{
		List<Person> people = new ArrayList<Person>();
		for(int i = 0; i < objs.length(); i ++)
		{
			JSONObject personObject = objs.getJSONObject(i);
			people.add(mapPerson(personObject));
		}
		return people;
	}
	
	public static Joke mapJoke(JSONObject obj) throws JSONException
	{
		Joke joke = new Joke();
		joke.setId(obj.getString("_id"));
		joke.setEtag(obj.getString("etag"));
		joke.setAuthorId(obj.getString("author_id"));
		joke.setCategory(obj.getString("category"));
		joke.setLanguage(obj.getString("language"));
		joke.setTitle(obj.getString("title"));
		joke.setContent(obj.getString("content"));
		joke.setCommentsNumber(obj.getInt("comments_number"));
		joke.setLikesNumber(obj.getInt("likes_number"));
		joke.setDislikesNumber(obj.getInt("dislikes_number"));
		joke.setSharesNumber(obj.getInt("shares_number"));
		joke.setCreated(FunnyJokesUtils.getDateFromString(obj.getString("created")));
		joke.setUpdated(FunnyJokesUtils.getDateFromString(obj.getString("updated")));
		return joke;
	}
	
	public static List<Joke> mapJokes(JSONArray objs) throws JSONException
	{
		List<Joke> jokes = new ArrayList<Joke>();
		for(int i = 0; i < objs.length(); i ++)
		{
			JSONObject jokeObject = objs.getJSONObject(i);
			jokes.add(mapJoke(jokeObject));
		}
		return jokes;
	}
	
	public static Comment mapComment(JSONObject obj) throws JSONException
	{
		Comment comment = new Comment();
		comment.setJokeId(obj.getString("_id"));
		comment.setEtag(obj.getString("etag"));
		comment.setAuthorId(obj.getString("author_id"));
		comment.setContent(obj.getString("content"));
		comment.setCreated(FunnyJokesUtils.getDateFromString(obj.getString("created")));
		comment.setUpdated(FunnyJokesUtils.getDateFromString(obj.getString("updated")));
		return comment;
	}
	
	public static List<Comment> mapComments(JSONArray objs) throws JSONException
	{
		List<Comment> comments = new ArrayList<Comment>();
		for(int i = 0; i < objs.length(); i ++)
		{
			JSONObject commentObject = objs.getJSONObject(i);
			comments.add(mapComment(commentObject));
		}
		return comments;
	}
	
	public static Like mapLike(JSONObject obj) throws JSONException
	{
		Like like = new Like();
		like.setJokeId(obj.getString("_id"));
		like.setEtag(obj.getString("etag"));
		like.setAuthorId(obj.getString("author_id"));
		like.setLiked(obj.getBoolean("liked"));
		like.setCreated(FunnyJokesUtils.getDateFromString(obj.getString("created")));
		like.setUpdated(FunnyJokesUtils.getDateFromString(obj.getString("updated")));
		return like;
	}
	
	public static List<Like> mapLikes(JSONArray objs) throws JSONException
	{
		List<Like> likes = new ArrayList<Like>();
		for(int i = 0; i < objs.length(); i ++)
		{
			JSONObject likeObject = objs.getJSONObject(i);
			likes.add(mapLike(likeObject));
		}
		return likes;
	}
}
