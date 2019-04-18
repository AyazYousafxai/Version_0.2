package com.tuts.prakash.simpleocr;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;

public class VideoView extends AppCompatActivity {
    View VideoView;
  FloatingActionButton back,close;
    private MediaController mMediaController;
    android.widget.VideoView video;
    int milisecond;
    int second=0;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
      second=getIntent().getExtras().getInt("Value");
        //second=0;
        video=findViewById(R.id.videoView);
       // Log.i("1st", String.valueOf(second));
textView=findViewById(R.id.textView);
        textView.setText(String.valueOf(second));
        back=findViewById(R.id.back);
        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        setVideoView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setVideoView() {

        milisecond=0;
       // second=second;

        mMediaController = new MediaController(VideoView.this);
       // mMediaController.setAnchorView(video);
        video.setMediaController(mMediaController);

        second=second+3;
       // Log.i("1st", String.valueOf(second));
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.control);
        video.setVideoURI(videoUri);
     video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the videoView that acts as the anchor for the MediaController
                        mMediaController.setAnchorView(video);
                    }
                });
            }
        });
        milisecond=second*1000;
      //  Log.i("1st", String.valueOf(milisecond));


        video.seekTo(milisecond);
        video.start();



        //mMediaController.setVisibility(View.GONE);

     /*   videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (videoView.isPlaying()) {

                    videoView.pause();
                   // mMediaController.hide();
                    return false;
                } else {
                    videoView.seekTo(50000);
                    videoView.start();

                  //  mMediaController.show(50000);
                   // mMediaController.hide();
                    return false;
                }
            }
        });*/
      video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                video.start();
            }
        });

    }
}
