package com.example.orderit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoTutorial extends AppCompatActivity {
    ColorDrawable colorDrawable;
    ActionBar bar;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_layout);
        bar = getSupportActionBar();

        colorDrawable = new ColorDrawable(Color.parseColor("#00995c"));
        bar.setBackgroundDrawable(colorDrawable);

        final VideoView videoView = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.tutorialblue;
        final Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        final MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

         //Setup a play button to start the video
        final ImageButton mPlayButton = (ImageButton)findViewById(R.id.play_button);

        // Create animations with ObjectAnimator
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mPlayButton,"rotation", 500);
        final ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(mPlayButton, "scaleX", 0f);
        final ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(mPlayButton, "scaleY", 0f);
        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(mPlayButton, View.ALPHA, 0.5f,0);

        // Condition for video playing
        final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    alphaAnimation.setDuration(500);
                    alphaAnimation.start();
                   // mPlayButton.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }

        };

        videoView.setOnInfoListener(onInfoToPlayStateListener);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(videoView.isPlaying()) {// animations using ObjectAnimator
                    objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    objectAnimator.setDuration(1000);
                    objectAnimator.start();
                    scaleDownX.setDuration(900);
                    scaleDownY.setDuration(900);
                    AnimatorSet scaleDown = new AnimatorSet();
                    scaleDown.play(scaleDownX).with(scaleDownY);
                    scaleDown.start();
                    //mPlayButton.setVisibility(View.GONE);
                }
                else {
                    videoView.start();
                    // show the media controls
                    mediaController.show();
                    // animations using ObjectAnimator
                    objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    objectAnimator.setDuration(1000);
                    objectAnimator.start();
                    scaleDownX.setDuration(900);
                    scaleDownY.setDuration(900);
                    AnimatorSet scaleDown = new AnimatorSet();
                    scaleDown.play(scaleDownX).with(scaleDownY);
                    scaleDown.start();
                    // hide button once playback starts
                    //mPlayButton.setVisibility(View.GONE);
                }
            }
        });
    }
}