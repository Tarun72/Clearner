package com.clearner.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.Evil.clearner.R;
import com.syntax.highlighter.PrettifyHighlighter;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Code extends Fragment {
	private String Program;
    private TextView CodeShow;
	public Code(String program) {
		// TODO Auto-generated constructor stub
		this.Program = program;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.fragment_programe_show, container, false);
		return root;
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	CodeShow = (TextView) getView().findViewById(R.id.programShowTextView);
	PrettifyHighlighter highlighter = new PrettifyHighlighter();
	
	BufferedReader rdr = new BufferedReader(new StringReader(Program));
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
		CodeShow.append(Html.fromHtml(highlighted[i]));
		CodeShow.append("\n");
		i = i++;
		}
		
//	CodeShow.setText(Program);
	CodeShow.setCustomSelectionActionModeCallback(new Callback() {
		
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
			int startSelections=CodeShow.getSelectionStart();
			int endSelections=CodeShow.getSelectionEnd();
			String selectedText = CodeShow.getText().toString().substring(startSelections, endSelections);
		
			switch(item.getItemId()){
			case R.id.share :
				System.out.println("selectedText startSelections endSelections"+startSelections+endSelections+selectedText);
				
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, selectedText);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
				break;
			case R.id.Search:
			
				Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
				intent.putExtra(SearchManager.QUERY, selectedText); // query contains search string
				startActivity(intent);
				break;
			}
			
			return true;
		}
	});
	}
	
}
