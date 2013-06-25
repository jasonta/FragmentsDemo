package com.sandiegogdg.fragmentsdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Encapsulates the details fragment.
 */
public class DetailsActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			DetailsFragment df = new DetailsFragment();
			df.setArguments(getIntent().getExtras());
			FragmentTransaction trans = getSupportFragmentManager()
					.beginTransaction();
			trans.add(android.R.id.content, df);
			trans.commit();
		}
	}
}
