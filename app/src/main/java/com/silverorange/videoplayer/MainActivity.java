package com.silverorange.videoplayer;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {

    ArrayList<VedioItem> videoList = new ArrayList<>();
    NetworkingService networkingService;
    JsonService  jsonService;
    VideoView videoView;
    TextView textView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        textView = findViewById(R.id.textView);

        networkingService = ((MyApp) getApplication()).getNetworkingService();
        jsonService = ( (MyApp)getApplication()).getJsonService();
        networkingService.fetchVideos();
        networkingService.listener = this;

        if (mediaController == null) {
            // create an object of media controller class
            mediaController = new MediaController(MainActivity.this);
            mediaController.show();
            mediaController.setAnchorView(videoView);
        }
        // set the media controller for video view
        videoView.setMediaController(mediaController);
        for(int i=0;i<videoList.size()-1;i++){
            videoView.setVideoPath(videoList.get(i).getVedio());// Getting first video link from array of videoItems.
            textView.setText(videoList.get(i).getDescription());
            videoView.start();
        }

        // implement on completion listener on video view
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when the video is completed
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Something went wrong. Please retry !!", Toast.LENGTH_LONG).show(); // display a toast when any error occurs while playing a video
                return false;
            }
        });
    }


    @Override
    public void APINetworkListener(String jsonString) {

        //getting JSON string as an argument and converting it to an array of objects of type 'VideoItem'
        videoList =  jsonService.parseVideoList(jsonString); // calling JsonService function to parse JSOn string

    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

    }
}