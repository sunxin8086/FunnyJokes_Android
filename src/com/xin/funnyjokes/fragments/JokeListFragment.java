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

import com.xin.funnyjokes.R;
import com.xin.funnyjokes.activities.MainActivity;
import com.xin.funnyjokes.adapters.JokeListAdapter;
import com.xin.funnyjokes.models.Joke;
import com.xin.funnyjokes.services.FunnyJokesDataProvider;
import com.xin.funnyjokes.services.FunnyJokesResponseHandler;
import com.xin.funnyjokes.services.FunnyJokesSmartDataProvider;
import com.xin.funnyjokes.services.JokeSearchQuery;

public class JokeListFragment extends Fragment implements PullToRefreshAttacher.OnRefreshListener {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String TAG = "JokeListFragment";
	public static final String ARG_QUERY = "query";

	private JokeSearchQuery query;

	private FunnyJokesDataProvider dataProvider;
	private PullToRefreshAttacher mPullToRefreshAttacher;

	// Begin Views
	private ListView jokeListView;

	// End Views

	public JokeListFragment() {
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Create called");
		mPullToRefreshAttacher = ((MainActivity) getActivity()).getPullToRefreshAttacher();
		dataProvider = new FunnyJokesSmartDataProvider();
		query = (JokeSearchQuery) this.getArguments().getParcelable(JokeListFragment.ARG_QUERY);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "Create View called");
		View rootView = inflater.inflate(R.layout.fragment_joke_list, container, false);
		jokeListView = (ListView) rootView.findViewById(R.id.joke_list);
		mPullToRefreshAttacher.addRefreshableView(jokeListView, this);
		mPullToRefreshAttacher.setRefreshing(true);
		this.onRefreshStarted(jokeListView);
		
		return rootView;
	}

	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		System.out.println("Saving state" + query.toString());
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewStateRestored(savedInstanceState);
		System.out.println("Saving state" + query.toString());
	}

	@Override
	public void onRefreshStarted(View view) {
		dataProvider.getJokes(query, 1, new FunnyJokesResponseHandler<List<Joke>>() {
			@Override
			public void onSuccess(List<Joke> jokes) {
				mPullToRefreshAttacher.setRefreshComplete();
				jokeListView.setAdapter(new JokeListAdapter(getActivity(), 0, jokes));
			}

			@Override
			public void onFailure(Throwable e) {
				// TODO Auto-generated method stub
				Log.e(TAG, e.toString());
				mPullToRefreshAttacher.setRefreshComplete();
			}
		});

	}

}