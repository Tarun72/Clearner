package com.clearner.Pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.clearner.fragment.Code;
import com.clearner.fragment.Output;
public class TabsPagerAdapter extends FragmentPagerAdapter {
	private String Code;
	private String Output;

	public TabsPagerAdapter(FragmentManager fm, String Code, String Output) {
		super(fm);
		this.Code = Code;
		this.Output = Output;
	}

	public Fragment getItem(int Index) {
		switch (Index) {
		case 0:
			// Top Rated fragment activity
			return new Code(Code);
		case 1:
			// Games fragment activity
			return new Output(Output);
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
}
