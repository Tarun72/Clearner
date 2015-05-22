package com.Evil.clearner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.Evil.clearner.ClearnerApplication.TrackerName;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class Keywords extends Activity implements OnItemClickListener {
	private ListView list;
	private String needtoshow;
	private String[]  new_ad = {"int","char","float","double",
			"signed","unsigned","short","long",
			"For","While","Do","typedef",
			"Enum","Break","continue","Goto"
			};
	private Integer[] img ={
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher
			
	};
	private  String[] ad = { "auto",
			"break", "case", "char", "const", "continue",
			"default", "do", "double", "else", "enum", "extern", "float",
			"for", "goto", "if", "int", "long", "register", "return", "short",
			"sizeof", "static", "struct", "switch", "typedef", "union",
			"unsigned", "void", "while",

	};
	
	private Integer[] imageres = {
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher
	};
// get tracker
	Tracker t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_theory);
		
		t = ((ClearnerApplication)Keywords.this.getApplication()).getTracker(
		        TrackerName.APP_TRACKER);
  
		// Set screen name.
        // Where path is a String representing the screen name.
        t.setScreenName("Introduction");// This event will also be sent with &cd=Home%20Screen.
     // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
        
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
        	needtoshow = bundle.getString("needtoshow");
        }
	}

@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
	if(needtoshow.equals("")){
	Customlist adapter = new Customlist(Keywords.this, ad, imageres);
	list = (ListView) findViewById(R.id.list);
	list.setAdapter(adapter);
	list.setOnItemClickListener(this);
	}else{
		Customlist adapter = new Customlist(Keywords.this, new_ad, img);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		
	}
	
	AnimationSet set = new AnimationSet(true);

	Animation animation = new AlphaAnimation(0.0f, 1.0f);
	animation.setDuration(300);
	set.addAnimation(animation);

	animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
			Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
			-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
	animation.setDuration(100);
	set.addAnimation(animation);

	LayoutAnimationController controller = new LayoutAnimationController(
			set, 0.5f);
	ListView listView = list;
	listView.setLayoutAnimation(controller);

}

@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	if(needtoshow.equals("")){
		System.out.println("in the if block");
	String item = ad[position];
	System.out.println("" + ad[position]);
	Intent in = new Intent(this, KeywordActivity.class);
	in.putExtra("topic", item);
	in.putExtra("database", "C_Keywords_Table");
	// in.putExtra("currentIntent", "CProgramList.class");
//	Keywords.this.finish(); // finish this activity
	startActivity(in);
	}else if(needtoshow.equals("yo")){
		System.out.println("in the else if ");
		String item = new_ad[position];
		System.out.println("" + new_ad[position]);
		Intent in = new Intent(this, Card.class);
		in.putExtra("topic", item);
		in.putExtra("database", "KEYWORDSSUMMARY");
		// in.putExtra("currentIntent", "CProgramList.class");
//		Keywords.this.finish(); // finish this activity
		startActivity(in);
		finish();
	}
}
}
