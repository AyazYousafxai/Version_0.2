package com.tuts.prakash.simpleocr;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

public class VideoView extends AppCompatActivity {
    View VideoView;
    Button back;
    private MediaController mMediaController;
    android.widget.VideoView video;
    int second,milisecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        second=getIntent().getExtras().getInt("Value");
        back=findViewById(R.id.back);
        video=findViewById(R.id.videoView);
        mMediaController = new MediaController(VideoView.this);
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
        second=second+1;


        mMediaController.setAnchorView(video);
        video.setMediaController(mMediaController);
        second=second+3;
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.control);
        video.setVideoURI(videoUri);
        milisecond=second*1000;
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
    }
}
