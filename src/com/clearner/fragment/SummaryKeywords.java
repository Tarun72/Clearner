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

public class SummaryKeywords extends Fragment {
	private String summary;
	TextView textviewsummary;
	public SummaryKeywords(String summary) {
	this.summary = summary;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_programe_show, container, false);
		System.out.println("is we are using this ?");
		return rootView;
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		textviewsummary = (TextView) getView().findViewById(R.id.programShowTextView);

		PrettifyHighlighter highlighter = new PrettifyHighlighter();
		
		BufferedReader rdr = new BufferedReader(new StringReader(summary));
		List<String> lines = new ArrayList<String>();
		try {
		for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
			lines.add(line);
			System.out.println("The buffer reader line" + line);
		}
		
			rdr.close();
//			18008334002
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
			textviewsummary.append(Html.fromHtml(highlighted[i]));
			textviewsummary.append("\n");
			i = i++;
			}

	}
}
