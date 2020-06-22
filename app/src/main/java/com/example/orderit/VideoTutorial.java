package com.example.orderit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.tutorial1;
        final Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        final MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

         //Setup a play button to start the video
        final ImageButton mPlayButton = (ImageButton)findViewById(R.id.play_button);
        final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    mPlayButton.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }

        };

        videoView.setOnInfoListener(onInfoToPlayStateListener);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(videoView.isPlaying()) {
                    mPlayButton.setVisibility(View.GONE);
                }
                else {
                    videoView.start();
                    // show the media controls
                    mediaController.show();
                    // hide button once playback starts
                    mPlayButton.setVisibility(View.GONE);
                }
            }
        });
    }
}