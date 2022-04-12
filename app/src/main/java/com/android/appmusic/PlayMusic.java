package com.android.appmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayMusic extends AppCompatActivity {
    TextView txtTitle, txtTimeSongLeft, txtTimeSongRight;
    SeekBar skBarSong;
    ImageButton btnPrev,btnPlay, btnLoop, btnNext;
    ArrayList<Song> arraySong = null;
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView imageViewDVD;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        Mapping();
        Intent intent = getIntent();
        position = intent.getIntExtra("dulieu",5);
        addListSongs();
        CreateMediaSong();
        animation = AnimationUtils.loadAnimation(this,R.anim.dvd_rorate);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageViewDVD.clearAnimation();
                    btnPlay.setImageResource(R.drawable.play);
                }else{
                    mediaPlayer.start();
                    imageViewDVD.startAnimation(animation);
                    btnPlay.setImageResource(R.drawable.pause);
                }

                SetTimeRight();
                SetTimeLeft();
            }
        });

        btnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isLooping()){
                    mediaPlayer.setLooping(false);
                    btnLoop.setImageResource(R.drawable.reload);
                }else{
                    mediaPlayer.setLooping(true);
                    btnLoop.setImageResource(R.drawable.refresh);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position > arraySong.size()-1){
                    position = 0;
                }else{
                    position++;
                }

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }else{
                    btnPlay.setImageResource(R.drawable.pause);
                }
                CreateMediaSong();
                mediaPlayer.start();
                imageViewDVD.startAnimation(animation);
                SetTimeRight();
                SetTimeLeft();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position < 0){
                    position = arraySong.size()-1;
                }else{
                    position--;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }else{
                    btnPlay.setImageResource(R.drawable.pause);
                }
                CreateMediaSong();
                mediaPlayer.start();
                imageViewDVD.startAnimation(animation);
                SetTimeRight();
                SetTimeLeft();
            }
        });


        skBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }
    private void CreateMediaSong(){
        mediaPlayer = MediaPlayer.create(PlayMusic.this,arraySong.get(position).getFileMp3());
        txtTitle.setText(arraySong.get(position).getName() + " - " + arraySong.get(position).getSinger());
        imageViewDVD.setImageResource(arraySong.get(position).getImg());
    }
    private void Mapping(){
        txtTimeSongLeft = (TextView) findViewById(R.id.TextViewTimeLeft);
        txtTimeSongRight = (TextView) findViewById(R.id.TextViewTimeRight);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        skBarSong = (SeekBar) findViewById(R.id.seekbarTime);
        btnNext = (ImageButton) findViewById(R.id.nextbtn);
        btnPrev = (ImageButton) findViewById(R.id.prevbtn);
        btnPlay = (ImageButton) findViewById(R.id.playbtn);
        btnLoop = (ImageButton) findViewById(R.id.btnloop);
        imageViewDVD = (ImageView) findViewById(R.id.imageViewDvd);
    }
    private void SetTimeRight(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
        txtTimeSongRight.setText(timeFormat.format(mediaPlayer.getDuration()));
        skBarSong.setMax(mediaPlayer.getDuration());
    }

    private void SetTimeLeft(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                txtTimeSongLeft.setText(timeFormat.format(mediaPlayer.getCurrentPosition()));
                skBarSong.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(position > arraySong.size()-1){
                            position = 0;
                        }else{
                            position++;
                        }

                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }else{
                            btnPlay.setImageResource(R.drawable.pause);
                        }
                        CreateMediaSong();
                        mediaPlayer.start();
                        SetTimeRight();
                        SetTimeLeft();
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);

    }
    public void addListSongs(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song("To the moon", "Hooligan","Sad Song",R.drawable.tothemoon, R.raw.tothemoon));
        arraySong.add(new Song("Dacing with your ghost", "Sasha Alex Sloan","Sad Song",R.drawable.dancingwithyourghost, R.raw.dancingwithyoughost));
        arraySong.add(new Song("Double take", "Dhruv","Sad Song",R.drawable.doubletake, R.raw.doubletake));
        arraySong.add(new Song("I love you 3000", "Stephanie Poetri","Sad Song",R.drawable.iloveuou, R.raw.iloveyou));
        arraySong.add(new Song("Love is gone", "Slander","Sad Song",R.drawable.loveisgone, R.raw.loveisgone));
        arraySong.add(new Song("See you again", " Wiz Khalifa","Sad Song",R.drawable.seeyouagain, R.raw.seeyouagain));
        arraySong.add(new Song("Something else", "Gary Jules","Sad Song",R.drawable.sonethingelse, R.raw.somethingelse));
        arraySong.add(new Song("You are the reason", "Calum Scott","Sad Song",R.drawable.youarethereason, R.raw.youarethereason));
        arraySong.add(new Song("Bad Liar", "Imagine Dragons","Sad Song",R.drawable.barliar, R.raw.barliar));
        arraySong.add(new Song("So far away", "David Guetta","Sad Song",R.drawable.sofaraway, R.raw.sofaraway));
    }
}