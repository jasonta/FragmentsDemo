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
public class MultipleFragmentsOneActivity extends FragmentActivity implements
		TitlesFragment.OnTitleSelectedListener {

	private final static String TITLES_FRAGMENT_TAG = "titles";
	private final static String DETAILS_FRAGMENT_TAG = "details";
	private final static int TITLES_ID = 101;
	private final static int DETAILS_ID = 102;

	private boolean mShowingBothFragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_activity);

		// if we're being restored from a previous state then we don't need to
		// do anything and should return or else we might end up with
		// overlapping fragments.
		if (savedInstanceState != null) {
			return;
		}

		if (isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_LARGE)) {
			showBothFragments();
		} else {
			showOneFragment();
		}
	}

	/**
	 * Mirrors SDK method, API 11+
	 * 
	 * @param size
	 * @return
	 */
	private boolean isLayoutSizeAtLeast(int size) {
		Configuration config = getResources().getConfiguration();
		final int screenSize = config.screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;
		return (screenSize == Configuration.SCREENLAYOUT_SIZE_UNDEFINED) ? false
				: screenSize >= size;
	}

	private void showOneFragment() {
		mShowingBothFragments = false;

		FragmentManager fm = getSupportFragmentManager();
		TitlesFragment titlesFragment = new TitlesFragment();
		FragmentTransaction trans = fm.beginTransaction();
		trans.add(android.R.id.content, titlesFragment, TITLES_FRAGMENT_TAG);
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
		titlesFragment = (TitlesFragment) fm.findFragmentById(TITLES_ID);
		args.putInt(DetailsFragment.ARG_INDEX, 0);
		detailsFragment.setArguments(args);
		trans = fm.beginTransaction();
		trans.add(DETAILS_ID, detailsFragment, DETAILS_FRAGMENT_TAG);
		trans.commit();
		layout.addView(detailsLayout);
	}

	@Override
	public void onTitleSelected(int position) {
		if (mShowingBothFragments) {
			// change content of details fragment
			FragmentManager fm = getSupportFragmentManager();
			DetailsFragment details = (DetailsFragment)
					fm.findFragmentById(DETAILS_ID);
			if (details == null || details.getCurrentIndex() != position) {
				details = DetailsFragment.create(position);

				FragmentTransaction trans = fm.beginTransaction();
				trans.replace(DETAILS_ID, details);
				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				trans.commit();
			}
		} else {
			// replace titles fragment with details fragment
			FragmentManager fm = getSupportFragmentManager();
			DetailsFragment details = (DetailsFragment)
					fm.findFragmentById(TITLES_ID);
			if (details == null || details.getCurrentIndex() != position) {
				details = DetailsFragment.create(position);

				FragmentTransaction trans = fm.beginTransaction();
				trans.replace(
						fm.findFragmentByTag(TITLES_FRAGMENT_TAG).getId(),
						details);
				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				// need to add to the back stack so that the back key will go
				// back to the titles fragment
				trans.addToBackStack(null);
				trans.commit();
			}
		}
	}
}
