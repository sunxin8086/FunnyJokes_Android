package com.xin.funnyjokes.adapters;

import java.util.List;

import com.xin.funnyjokes.R;
import com.xin.funnyjokes.models.Joke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class JokeListAdapter extends ArrayAdapter<Joke> {

	private List<Joke> jokes;
	private Context context;

	public JokeListAdapter(Context context, int resource, List<Joke> jokes) {
		super(context, resource, jokes);
		this.jokes = jokes;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View jokeView = convertView;
		if (jokeView == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			jokeView = vi.inflate(R.layout.joke_list_item, null);
		}
		Joke joke = jokes.get(position);
		if (joke != null) {
			TextView content = (TextView) jokeView.findViewById(R.id.joke_content);
			TextView author = (TextView) jokeView.findViewById(R.id.joke_author);
			TextView createdTime = (TextView) jokeView.findViewById(R.id.joke_created_time);
			if (content != null) {
				content.setText(joke.getContent());
			}
			if (author != null) {
				author.setText(joke.getAuthorId());
			}
			if (createdTime != null) {
				createdTime.setText(joke.getCreated().toString());
			}
		}
		return jokeView;
	}

}
