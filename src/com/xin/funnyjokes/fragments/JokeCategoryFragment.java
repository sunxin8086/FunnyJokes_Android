package com.xin.funnyjokes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xin.funnyjokes.R;
import com.xin.funnyjokes.adapters.JokeSectionsPagerAdapter;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class JokeCategoryFragment extends Fragment {
    public static final String ARG_JOKE_CATEGORY = "joke_category";
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	JokeSectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

    public JokeCategoryFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke_category, container, false);
        String category = getArguments().getString(ARG_JOKE_CATEGORY);

        // Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new JokeSectionsPagerAdapter(
				getChildFragmentManager(), category);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
		mViewPager.setOffscreenPageLimit(4);
		mViewPager.setAdapter(mSectionsPagerAdapter);
        return rootView;
    }
}