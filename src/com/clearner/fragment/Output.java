package com.clearner.fragment;

import com.Evil.clearner.R;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Output extends Fragment {
	private String output;
	private TextView outTextView;

	public Output(String output) {
		// TODO Auto-generated constructor stub
		this.output = output;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_programe_show,
				container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		outTextView = (TextView) getView().findViewById(
				R.id.programShowTextView);
		outTextView.setText(output);
		outTextView.setCustomSelectionActionModeCallback(new Callback() {

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.custom_menu, menu);

				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// TODO Auto-generated method stub
				int startSelections = outTextView.getSelectionStart();
				int endSelections = outTextView.getSelectionEnd();
				String selectedText = outTextView.getText().toString()
						.substring(startSelections, endSelections);

				switch (item.getItemId()) {
				case R.id.share:
					System.out
							.println("selectedText startSelections endSelections"
									+ startSelections
									+ endSelections
									+ selectedText);

					Intent sendIntent = new Intent();
					sendIntent.setAction(Intent.ACTION_SEND);
					sendIntent.putExtra(Intent.EXTRA_TEXT, selectedText);
					sendIntent.setType("text/plain");
					startActivity(sendIntent);
					break;
				case R.id.Search:

					Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
					intent.putExtra(SearchManager.QUERY, selectedText); // query
																		// contains
																		// search
																		// string
					startActivity(intent);
					break;
				}
				return true;

			}
		});
	}
}
