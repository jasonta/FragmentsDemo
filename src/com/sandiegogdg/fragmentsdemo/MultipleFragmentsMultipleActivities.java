package com.sandiegogdg.fragmentsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Handle titles fragment internally, but start a separate activity, if not 
 * dual-pane, to display details fragment.
 */
public class MultipleFragmentsMultipleActivities extends FragmentActivity
		implements TitlesFragment.OnTitleSelectedListener {

	private static final String DETAILS_FRAGMENT_TAG = "details";

	private static final String ARG_CURRENT_INDEX = "currentIndex";
	private static final String ARG_SHOWN_INDEX = "shownIndex";

	private int mCurrentIndex = 0;
	private int mShownIndex = -1;
	private boolean mDualPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiple_activities);

		if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(ARG_CURRENT_INDEX);
			mShownIndex = savedInstanceState.getInt(ARG_SHOWN_INDEX);
		}

		final View detailsFrame = findViewById(R.id.detailsLayout);
		mDualPane = (detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE);

		if (mDualPane) {
			showDetails(mCurrentIndex);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ARG_CURRENT_INDEX, mCurrentIndex);
		outState.putInt(ARG_SHOWN_INDEX, mShownIndex);
	}

	@Override
	public void onTitleSelected(final int position) {
		showDetails(position);
	}

	private void showDetails(final int index) {
		mCurrentIndex = index;
		if (mDualPane) {
			// change content of details fragment
			if (mShownIndex != mCurrentIndex) {
				mShownIndex = mCurrentIndex;
				FragmentManager fm = getSupportFragmentManager();
				DetailsFragment df = DetailsFragment.create(index);
				FragmentTransaction trans = fm.beginTransaction();
				// we don't actually use tags in this app, but it could come
				// in handy if we need to find one via findFragmentByTag in
				// the future
				trans.replace(R.id.detailsLayout, df, DETAILS_FRAGMENT_TAG);
				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				trans.commit();
			}
		} else {
			Intent intent = new Intent(this, DetailsActivity.class);
			intent.putExtra(DetailsFragment.ARG_INDEX, index);
			startActivity(intent);
		}
	}
}
