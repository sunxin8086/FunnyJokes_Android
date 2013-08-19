package com.xin.funnyjokes.adapters;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xin.funnyjokes.fragments.JokeListFragment;
import com.xin.funnyjokes.services.JokeSearchQuery;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
 * of the sections/tabs/pages.
 */
public class JokeSectionsPagerAdapter extends FragmentPagerAdapter {

	private String category;
	private Fragment[] fragments = new Fragment[5]; 
	
	public JokeSectionsPagerAdapter(FragmentManager fm, String category) {
		super(fm);
		this.category = category;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		
		Fragment fragment = fragments[position];
		if(fragment != null)
			return fragment;
		
		fragment = new JokeListFragment();
		fragments[position] = fragment;
		Bundle args = new Bundle();
		JokeSearchQuery query = new JokeSearchQuery();
		query.setCategory(category);
		
		switch (position) {
		case 0:
			query.setDays(0);
			break;
		case 1:
			query.setDays(1);
			break;
		case 2:
			query.setDays(7);
			break;
		case 3:
			query.setDays(30);
			break;
		case 4:
			query.setDays(JokeSearchQuery.MAX_DAYS);
		default:
			query.setDays(0);
			break;
		}
		args.putParcelable(JokeListFragment.ARG_QUERY, query);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// Show 5 total pages.
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return "Latest";// Resources.getSystem().getString(R.string.title_section1).toUpperCase(l);
		case 1:
			return "Daily Top";// Resources.getSystem().getString(R.string.title_section2).toUpperCase(l);
		case 2:
			return "Weekly Top";// Resources.getSystem().getString(R.string.title_section3).toUpperCase(l);
		case 3:
			return "Monthly Top";// Resources.getSystem().getString(R.string.title_section3).toUpperCase(l);
		case 4:
			return "All Time Top";// Resources.getSystem().getString(R.string.title_section3).toUpperCase(l);
		}
		return null;
	}
}
