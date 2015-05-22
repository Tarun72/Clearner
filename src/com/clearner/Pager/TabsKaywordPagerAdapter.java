package com.clearner.Pager;

import java.util.ArrayList;

import com.clearner.fragment.QuickLook;
import com.clearner.fragment.SummaryKeywords;
import com.clearner.fragment.WhatIskeyword;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class TabsKaywordPagerAdapter extends FragmentPagerAdapter {
	FragmentManager manager;
	ArrayList<String> list;

	public TabsKaywordPagerAdapter(FragmentManager fm, ArrayList<String> list) {
		// TODO Auto-generated constructor stub
		super(fm);
		this.list = list;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Fragment getItem(int Index) {
		switch (Index) {
		case 0:
		return	new SummaryKeywords(list.get(1));
		/*case 1:
			return new WhatIskeyword(list.get(2));
		case 2:
		return	new QuickLook(list.get(2));
		*/}
		return null;

	}


}
