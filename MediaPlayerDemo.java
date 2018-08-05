package package_name;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MediaPlayerDemo extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{
    TextView t1,t2;
    ImageButton play;
    SeekBar sp;
    Thread s;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_demo);

        play=(ImageButton) findViewById(R.id.mp);
        sp=(SeekBar)findViewById(R.id.sp);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView) findViewById(R.id.t2);
       // mp=MediaPlayer.create(this,R.raw.pqr);
        Bundle b=getIntent().getExtras();
        mp=MediaPlayer.create(this, Uri.parse(b.getString("song")));

        t2.setText(ConvertToDuration(mp.getDuration()));
        sp.setMax(mp.getDuration());
        s=new Thread(){
            public  void run(){
                while (mp.getCurrentPosition()!=mp.getDuration())
                {
                    sp.setProgress(mp.getCurrentPosition());
                }
            }
        };
        play.setOnClickListener(this);
        sp.setOnSeekBarChangeListener(this);
        s.start();


    }
    public String ConvertToDuration(long l){
        String dur="";
        l=l/1000;
        dur=l/60+":"+l%60;
        return dur;
    }

    @Override
    public void onClick(View view) {
        if (!mp.isPlaying())
        {
            mp.start();
            play.setImageResource(android.R.drawable.ic_media_pause);
        }
        else{
            mp.pause();
            play.setImageResource(android.R.drawable.ic_media_play);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b){
            mp.seekTo(i);
        }
        t1.setText(ConvertToDuration(mp.getCurrentPosition()));
        t2.setText(ConvertToDuration((mp.getDuration()-mp.getCurrentPosition())));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }



    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null){
            mp.stop();
            if (isFinishing()){
                mp.stop();
                mp.release();
            }
        }
    }


}
