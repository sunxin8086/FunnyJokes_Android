package com.xin.funnyjokes.fragments;

import java.util.List;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xin.funnyjokes.R;
import com.xin.funnyjokes.activities.MainActivity;
import com.xin.funnyjokes.adapters.JokeListAdapter;
import com.xin.funnyjokes.models.Joke;
import com.xin.funnyjokes.services.FunnyJokesDataProvider;
import com.xin.funnyjokes.services.FunnyJokesSmartDataProvider;
import com.xin.funnyjokes.services.FunnyJokesResponseHandler;

public class JokeListFragment extends Fragment implements PullToRefreshAttacher.OnRefreshListener {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NAME = "section_name";
	private PullToRefreshAttacher mPullToRefreshAttacher;
	private ListView jokeListView;

	public JokeListFragment() {
	}
	
	FunnyJokesDataProvider dataProvider;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_joke_list,
				container, false);
		jokeListView = (ListView) rootView
				.findViewById(R.id.joke_list);
		// The ScrollView is what we'll be listening to for refresh starts

        // Now get the PullToRefresh attacher from the Activity. An exercise to the reader
        // is to create an implicit interface instead of casting to the concrete Activity
		mPullToRefreshAttacher = ((MainActivity) getActivity()).getPullToRefreshAttacher();

        // Now set the ScrollView as the refreshable view, and the refresh listener (this)
        mPullToRefreshAttacher.addRefreshableView(jokeListView, this);
		dataProvider = new FunnyJokesSmartDataProvider();
		return rootView;
	}

	@Override
	public void onRefreshStarted(View view) {
		dataProvider.getJokes("1", 1, new FunnyJokesResponseHandler<List<Joke>>()
		{

			@Override
			public void onSuccess(List<Joke> t) {
				jokeListView.setAdapter(new JokeListAdapter(getActivity(), 0, t));
				mPullToRefreshAttacher.setRefreshComplete();
				
			}

			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	
}