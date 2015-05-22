package com.Evil.clearner;

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

import com.database.clearner.ProgramDatabase;

public class ProgramList extends Clearner implements OnItemClickListener {

private ProgramDatabase PD;
private ListView lists;
private String[] list= {
		"Program to add two numbers using function (Pass by value method).",
        "Program to add two numbers using function (Pass by Reference).",
        "Progarm to calculate Factorial of a given number.",
        "Program to generate Fibonacci series.",
        "Arithmetic calculator using switch statement.",
        "Addition of Array elements.",
        "Search first occurrence of element in an array.",
        "Sort array in ascending order.",
        "Sort array in descending order.",
        "Reverse a given Number.",
        "Calculate Length of a String (without using strlen in-built function).",
        "Calculate Length of a String (Using strlen in-built function).",
        "Program to find whether a number is Armstrong or not.",
        "Program to Demonstrate Typecasting in C.",
        "Program to Demonstrate sscanf function in C.",
        "Program to Demonstrate sprintf function in C.",
        "Program to Demonstrate Pointer Operations.",
        "Program to demonstrate Structures in C Programming Language.",
        "Program to copy the contents of one array into another in the reverse order.",
        "Program to perform addition of two 2 by 2 matrix.",
        "Program to obtain Transpose of a Matrix.",
        "Program to find if a 3 by 3 square matrix is symmetric.",
        "Program to print binary equivalent of a given decimal integer.",
        "Program to count number of words from a given sentence.",
        "Program to create 10 different random numbers.",
        "Program to determine whether the character is entered is a capital letter, a small case letter, a digit or special symbol.",
        "Program that accept values in 2-Dimensional 3 by 3 array and displays the sum of all the elements."};
	
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
		R.drawable.ic_launcher
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_theory);
		init();
	}


	@Override
		protected void onStart() {
			super.onStart();
		}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String item= list[position];
		System.out.println(""+list[position]);
		Intent in= new Intent(this, ProgrameShow.class);
		in.putExtra("ID", position);
		in.putExtra("database", "C_Programs_Table");
		startActivity(in);			
	}
	
	@Override
		public void init() {
			super.init();
			PD = new ProgramDatabase(ProgramList.this);
			setTitle(getResources().getString(R.string.pro_name));

			Customlist adapter = new Customlist(ProgramList.this, list, imageres);
			lists = (ListView) findViewById(R.id.list);
			lists.setAdapter(adapter);
			lists.setOnItemClickListener(this);
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
			ListView listView = lists;
			listView.setLayoutAnimation(controller);


	}
}
