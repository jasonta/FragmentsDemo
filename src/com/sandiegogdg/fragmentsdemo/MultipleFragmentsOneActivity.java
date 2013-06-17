package com.sandiegogdg.fragmentsdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Using only one activity, choose during run-time which fragment(s) to display
 * depending on screen size and/or state.
 */
public class MultipleFragmentsOneActivity extends FragmentActivity {

	private final static String TITLES_FRAGMENT_TAG = "titles";
	private final static String DETAILS_FRAGMENT_TAG = "details";
	private final static int TITLES_ID = 101;
	private final static int DETAILS_ID = 102;

	private boolean mShowingBothFragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_activity);

		Configuration config = getResources().getConfiguration();
		final int screenSize = config.screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;
		if (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
			showOneFragment();
		}
		if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
			showBothFragments();
		}
	}

	private void showOneFragment() {
		mShowingBothFragments = false;

		FragmentManager fm = getSupportFragmentManager();
		TitlesFragment titlesFragment = new TitlesFragment();
		FragmentTransaction trans = fm.beginTransaction();
		trans.add(TITLES_ID, titlesFragment, TITLES_FRAGMENT_TAG);
		trans.commit();
	}

	private void showBothFragments() {
		mShowingBothFragments = true;

		// we need to add inner layouts to the main layout to account for
		// the weights of the fragments
		FragmentManager fm = getSupportFragmentManager();
		TitlesFragment titlesFragment = new TitlesFragment();
		LinearLayout layout = (LinearLayout) findViewById(R.id.one_activity_parent_layout);
		FrameLayout titlesLayout = new FrameLayout(this);
		titlesLayout.setId(TITLES_ID);
		titlesLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT, 2.0f));
		FragmentTransaction trans = fm.beginTransaction();
		trans.add(TITLES_ID, titlesFragment, TITLES_FRAGMENT_TAG);
		trans.commit();
		layout.addView(titlesLayout);

		FrameLayout detailsLayout = new FrameLayout(this);
		detailsLayout.setId(DETAILS_ID);
		detailsLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT, 3.0f));
		DetailsFragment detailsFragment = new DetailsFragment();
		Bundle args = new Bundle();
		args.putInt(DetailsFragment.ARG_INDEX, 0);
		detailsFragment.setArguments(args);
		trans = fm.beginTransaction();
		trans.add(DETAILS_ID, detailsFragment, DETAILS_FRAGMENT_TAG);
		trans.commit();
		layout.addView(detailsLayout);
	}
}
