package coding.mohammad.adcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MusicActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarText;

    FloatingActionButton fabPlayMusic;
    CardView cardMusicImg;

    MediaPlayer ring ;

    boolean isMusicPlaying = false;

    ObjectAnimator anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        findViews();
        setToolbar();

        ring = MediaPlayer.create(MusicActivity.this, R.raw.chartar);

        anim = ObjectAnimator.ofFloat(cardMusicImg, "rotation", 0, 360);
        anim.setDuration(10000);
        //anim.setRepeatCount(2);
        anim.setRepeatMode(ObjectAnimator.RESTART);

        fabPlayMusic.setOnClickListener(v -> {
            if (isMusicPlaying){
                stopMusic();
            }else{
                playMusic();
            }
        });

    }

    private void findViews(){
        toolbar = findViewById(R.id.toolbar);
        toolbarText = findViewById(R.id.toolbar_txt);
        fabPlayMusic = findViewById(R.id.fab_play_music);
        cardMusicImg = findViewById(R.id.card_music_image);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        toolbarText.setText("Play music");
    }

    private void playMusic() {
        ring.start();
        if (anim.isPaused()){
            anim.resume();
        }else{
            anim.start();
        }
        isMusicPlaying = true;
        fabPlayMusic.setImageResource(R.drawable.ic_baseline_stop_24);
    }

    private void stopMusic(){
        ring.stop();
        anim.pause();
        isMusicPlaying = false;
        fabPlayMusic.setImageResource(R.drawable.ic_baseline_play_arrow_24);
    }

}