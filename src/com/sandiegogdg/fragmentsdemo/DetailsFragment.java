package com.sandiegogdg.fragmentsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sandiegogdg.fragmentsdemo.Data.DetailsData;

public class DetailsFragment extends Fragment {

	/** Bundle argument used to display appropriate details data */
	public static final String ARG_INDEX = "index";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		// only create view if fragment is part of container, otherwise
		// it will not be displayed
		if (container != null) {
			ScrollView scroller = new ScrollView(getActivity());
			TextView text = new TextView(getActivity());
			int padding = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP,
					4, getActivity().getResources().getDisplayMetrics());
			text.setPadding(padding, padding, padding, padding);
			scroller.addView(text);
			final DetailsData dd = Data.DETAILS[getArguments().getInt(
					ARG_INDEX, 0)];
			text.setText(dd.text);
			text.setTextSize(24);
			text.setCompoundDrawablesWithIntrinsicBounds(
					null,
					getResources().getDrawable(dd.resId),
					null,
					null);
			return scroller;
		}
		return view;
	}

}
