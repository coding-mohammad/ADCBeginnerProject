package coding.mohammad.adcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TimerActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarText;

    FloatingActionButton fabTimer;
    TextView txtTimer;

    final long time = 30 * 1000;

    CountDownTimer count;

    boolean isCountDowning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        findViews();
        setToolbar();

        fabTimer.setOnClickListener(v -> {
            if (!isCountDowning){
                isCountDowning = true ;
                count = new CountDownTimer(time, 1000) {

                    public void onTick(long millisUntilFinished) {
                        txtTimer.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        txtTimer.setText("done!");
                        isCountDowning = false;
                    }
                }.start();
            }
        });


    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        toolbarText = findViewById(R.id.toolbar_txt);
        fabTimer = findViewById(R.id.fab_start_timer);
        txtTimer = findViewById(R.id.txt_timer);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbarText.setText("Timer");
    }

}