package okki.setyatmoko.guidepess.Sub.lvl1;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okki.setyatmoko.guidepess.BaseFragment;
import okki.setyatmoko.guidepess.Models.ItemDetails;
import okki.setyatmoko.guidepess.Models.Music;
import okki.setyatmoko.guidepess.R;

/**
 * Created by Hinata on 8/11/2017.
 */

public class FragmentItemMusic extends BaseFragment {
    private String title = "";
    private SeekBar seekBar;
    Music music;
    MediaPlayer mediaPlayer;
    TextView txtCurrent, txtTotal, txtContent;
    ImageButton btnPlay, btnPrev, btnNext;

    public static FragmentItemMusic newInstance(String title, Music music) {
        FragmentItemMusic fragment = new FragmentItemMusic();
        fragment.title = title;
        fragment.music = music;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getContext(), Uri.parse(music.getUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sub_type_music, container, false);
        btnPlay = (ImageButton) v.findViewById(R.id.btn_play);
        btnPrev = (ImageButton) v.findViewById(R.id.btn_prev);
        btnNext = (ImageButton) v.findViewById(R.id.btn_next);
        txtCurrent = (TextView) v.findViewById(R.id.txt_time_current);
        txtTotal = (TextView) v.findViewById(R.id.txt_time_total);
        txtContent = (TextView) v.findViewById(R.id.txtContent) ;
        seekBar = (SeekBar) v.findViewById(R.id.seekbar);
        seekBar.setPadding(0,0,0,0);

        seekBar.setMax(100);
        seekBar.setProgress(0);

        txtContent.setText(music.getLiric());

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                setAction();
                Log.e("onPrepared", "sssss");
            }
        });

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }

    private void setAction(){
        txtCurrent.setText(formateTime(mediaPlayer.getCurrentPosition()));
        txtTotal.setText(formateTime(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()){
            Glide.with(getContext()).load(R.drawable.pause).into(btnPlay);
        } else  Glide.with(getContext()).load(R.drawable.play).into(btnPlay);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    Glide.with(getContext()).load(R.drawable.play).into(btnPlay);
                    mediaPlayer.pause();
                } else {
                    Glide.with(getContext()).load(R.drawable.pause).into(btnPlay);
                    mediaPlayer.start();
                    startTimer();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Glide.with(getContext()).load(R.drawable.play).into(btnPlay);
            }
        });

    }

    private void startTimer(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Log.e("timer", mediaPlayer.getCurrentPosition()+"");
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                txtCurrent.setText(formateTime(mediaPlayer.getCurrentPosition()));
                if (mediaPlayer.isPlaying()) startTimer();
            }
        });
    }

    private String formateTime(int time){
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(date);
    }

    @Override
    public String getExpandedUrl() {
        return music.getImg();
    }

    @Override
    public boolean showBackButton() {
        return true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean transparantTitle() {
        return false;
    }

}
