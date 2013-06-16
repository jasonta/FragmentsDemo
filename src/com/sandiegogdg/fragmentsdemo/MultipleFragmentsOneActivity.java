package com.sandiegogdg.fragmentsdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Using only one activity, choose during run-time which fragment(s) to display
 * depending on screen size and/or state.
 */
public class MultipleFragmentsOneActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_activity);
	}

}
