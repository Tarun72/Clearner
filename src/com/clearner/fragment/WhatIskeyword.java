package com.clearner.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.Evil.clearner.R;
import com.syntax.highlighter.PrettifyHighlighter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WhatIskeyword extends Fragment {
	String Summary;
    TextView textView;
    String Split[];
	public WhatIskeyword(String Summary) {
		// TODO Auto-generated constructor stub
		this.Summary = Summary;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_programe_show, container,
				false);
		return rootView;
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		textView = (TextView) getView().findViewById(R.id.programShowTextView);
		
		Split = Summary.split("---");
		System.out.println("@Split"+Split.length);
		PrettifyHighlighter highlighter = new PrettifyHighlighter();
		
		BufferedReader rdr = new BufferedReader(new StringReader(Split[0]));
		List<String> lines = new ArrayList<String>();
		try {
		for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
			lines.add(line);
			System.out.println("The buffer reader line" + line);
		}
		
			rdr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String[] highlighted = {""};
	 
		int i = 0 ;
			StringBuilder builder = new StringBuilder();
			for (String details : lines) {
			   builder.append(details + "\n");
			highlighted[i] = highlighter.highlight("cpp", details);
			textView.append(Html.fromHtml(highlighted[i]));
			textView.append("\n");
			i = i++;
			}
	}
}
