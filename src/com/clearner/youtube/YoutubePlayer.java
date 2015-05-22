package com.clearner.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.Evil.clearner.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayer extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {
	private YouTubePlayer YPlayer;
	private static final String YoutubeDeveloperKey = "AIzaSyCNSw6IOggfSv_SY_ngVhCTS7T8DpBwHUs";
	private static final int RECOVERY_DIALOG_REQUEST = 1;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.youtube_player_activity);
		 YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		 youTubeView.initialize(YoutubeDeveloperKey, this);
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult errorReason) {
		// TODO Auto-generated method stub
		if (errorReason.isUserRecoverableError()) {
			 errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
			 } else {
			 String errorMessage = String.format(
			 "There was an error initializing the YouTubePlayer",
			 errorReason.toString());
			 Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
			 }
	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean wasRestored) {
		// TODO Auto-generated method stub
		if (!wasRestored) {
			 YPlayer = player;
			 /*
			 * Now that this variable YPlayer is global you can access it
			 * throughout the activity, and perform all the player actions like
			 * play, pause and seeking to a position by code.
			 */
			 YPlayer.cueVideo("32eywT-bQhQ");
			 }
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RECOVERY_DIALOG_REQUEST) {
			// Retry initialization if user performed a recovery action
			getYouTubePlayerProvider().initialize(YoutubeDeveloperKey, this);
		}
	}

	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}
}
