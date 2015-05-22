package com.clearner.Pager;

import java.util.ArrayList;
import com.clearner.fragment.Content;
import com.clearner.fragment.Deeper;
import com.clearner.fragment.QuickLook;
import com.clearner.fragment.Summary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerTheoryAdapter extends FragmentPagerAdapter {
private	ArrayList<String> Content;

	public TabsPagerTheoryAdapter(FragmentManager fm, ArrayList<String> Content) {
		super(fm);
		this.Content = Content;

	}

	@Override
	public Fragment getItem(int Index) {
		switch (Index) {
		case 0:
			return new Content(Content.get(0));
		case 1:
			return new Deeper(Content.get(1));
		case 2:
			return new QuickLook(Content.get(2));
		case 3:
			return new Summary(Content.get(3));
		}
		return null;
	}

	@Override
	public int getCount() {
		return 4;
	}

}
