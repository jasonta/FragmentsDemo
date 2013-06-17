package com.sandiegogdg.fragmentsdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Displays a list of titles, each of which can be clicked on to display
 * the contents using ContentFragment.
 */
public class TitlesFragment extends ListFragment {

	private static final String[] TITLES = {
			"Mercury",
			"Venus",
			"Earth",
			"Mars",
			"Jupiter",
			"Saturn",
			"Uranus",
			"Neptune",
	};

	@Override
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		TitleAdapter adapter = new TitleAdapter(getActivity());
		setListAdapter(adapter);

		/*
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			setListAdapter(new ArrayAdapter<String>(
					getActivity(),
					android.R.layout.simple_list_item_activated_1,
					TITLES));
		} else {
			setListAdapter(new ArrayAdapter<String>(
					getActivity(),
					android.R.layout.simple_list_item_1,
					TITLES));
		}
		*/
	}

	private static class TitleAdapter extends BaseAdapter {

		private LayoutInflater mLayoutInflater;

		public TitleAdapter(final Context context) {
			mLayoutInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Object getItem(int position) {
			return TITLES[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv;
			if (convertView == null) {
				View layout = mLayoutInflater.inflate(
						R.layout.title_item_layout, parent, false);
				tv = (TextView) layout.findViewById(R.id.titleText);
				tv.setText(TITLES[position]);
			} else {
				tv = (TextView) convertView;
			}
			return tv;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
