package com.Evil.clearner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.widget.Toast;

public class Clearner extends ActionBarActivity{
	public static final String AD_UNIT_ID = "ca-app-pub-3748442571221151/1824903024";

	public static void openActivity(Context context, Class<?> instance) {
		Intent intent = new Intent(context, instance);
		context.startActivity(intent);
	}
	
	public void init(){
	}

	public static void toastShow(String message, Context context) {
		Toast display = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		display.setGravity(Gravity.CENTER, 0, 0);
		display.show();
	}
}
