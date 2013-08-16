package com.xin.funnyjokes.activities;

import java.util.Locale;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xin.funnyjokes.R;

public class MainActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    
    private PullToRefreshAttacher mPullToRefreshAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.joke_categories_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        // The attacher should always be created in the Activity's onCreate
        mPullToRefreshAttacher = PullToRefreshAttacher.get(this);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
           selectItem(0);
        }
    }
    
    PullToRefreshAttacher getPullToRefreshAttacher() {
        return mPullToRefreshAttacher;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // getSupportActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new JokeCategoryFragment();
        Bundle args = new Bundle();
        args.putInt(JokeCategoryFragment.ARG_JOKE_CATEGORY, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class JokeCategoryFragment extends Fragment {
        public static final String ARG_JOKE_CATEGORY = "joke_category";
    	/**
    	 * The {@link android.support.v4.view.PagerAdapter} that will provide
    	 * fragments for each of the sections. We use a
    	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
    	 * will keep every loaded fragment in memory. If this becomes too memory
    	 * intensive, it may be best to switch to a
    	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
    	 */
    	SectionsPagerAdapter mSectionsPagerAdapter;

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
            int i = getArguments().getInt(ARG_JOKE_CATEGORY);
            String category = getResources().getStringArray(R.array.joke_categories_array)[i];

            // Create the adapter that will return a fragment for each of the three
    		// primary sections of the app.
    		mSectionsPagerAdapter = new SectionsPagerAdapter(
    				getChildFragmentManager());

    		// Set up the ViewPager with the sections adapter.
    		mViewPager = (ViewPager) rootView.findViewById(R.id.pager	);
    		mViewPager.setAdapter(mSectionsPagerAdapter);
            return rootView;
        }
        
        /**
    	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
    	 * one of the sections/tabs/pages.
    	 */
    	public class SectionsPagerAdapter extends FragmentPagerAdapter {

    		public SectionsPagerAdapter(FragmentManager fm) {
    			super(fm);
    		}

    		@Override
    		public Fragment getItem(int position) {
    			// getItem is called to instantiate the fragment for the given page.
    			// Return a DummySectionFragment (defined as a static inner class
    			// below) with the page number as its lone argument.
    			Fragment fragment = new DummySectionFragment();
    			Bundle args = new Bundle();
    			args.putInt(DummySectionFragment.ARG_SECTION_NAME, position + 1);
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
    				return getString(R.string.title_section1).toUpperCase(l);
    			case 1:
    				return getString(R.string.title_section2).toUpperCase(l);
    			case 2:
    				return getString(R.string.title_section3).toUpperCase(l);
    			}
    			return null;
    		}
    	}

    	/**
    	 * A dummy fragment representing a section of the app, but that simply
    	 * displays dummy text.
    	 */
    	public static class DummySectionFragment extends Fragment implements
        PullToRefreshAttacher.OnRefreshListener {
    		/**
    		 * The fragment argument representing the section number for this
    		 * fragment.
    		 */
    		public static final String ARG_SECTION_NAME = "section_name";
    		private PullToRefreshAttacher mPullToRefreshAttacher;

    		public DummySectionFragment() {
    		}

    		@Override
    		public View onCreateView(LayoutInflater inflater, ViewGroup container,
    				Bundle savedInstanceState) {
    			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
    					container, false);
    			TextView dummyTextView = (TextView) rootView
    					.findViewById(R.id.section_label);
    			ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.ptr_scrollview);
    			// The ScrollView is what we'll be listening to for refresh starts

                // Now get the PullToRefresh attacher from the Activity. An exercise to the reader
                // is to create an implicit interface instead of casting to the concrete Activity
    			mPullToRefreshAttacher = ((MainActivity) getActivity())
                        .getPullToRefreshAttacher();

                // Now set the ScrollView as the refreshable view, and the refresh listener (this)
                mPullToRefreshAttacher.addRefreshableView(scrollView, this);

    			
    			dummyTextView.setText(Integer.toString(getArguments().getInt(
    					ARG_SECTION_NAME)));
    			return rootView;
    		}

			@Override
			public void onRefreshStarted(View view) {
				Log.e("Test", "Worked");
				mPullToRefreshAttacher.setRefreshComplete();
				
			}
    	}
    }
  
}