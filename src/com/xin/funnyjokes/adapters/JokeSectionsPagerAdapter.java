package com.xin.funnyjokes.adapters;

import java.util.Locale;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xin.funnyjokes.R;
import com.xin.funnyjokes.fragments.JokeListFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class JokeSectionsPagerAdapter extends FragmentPagerAdapter {

	public JokeSectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		Fragment fragment = new JokeListFragment();
		Bundle args = new Bundle();
		args.putInt(JokeListFragment.ARG_SECTION_NAME, position + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return "Latest";//Resources.getSystem().getString(R.string.title_section1).toUpperCase(l);
		case 1:
			return "Daily Top";//Resources.getSystem().getString(R.string.title_section2).toUpperCase(l);
		case 2:
			return "Monthly Top";//Resources.getSystem().getString(R.string.title_section3).toUpperCase(l);
		}
		return null;
	}
}

