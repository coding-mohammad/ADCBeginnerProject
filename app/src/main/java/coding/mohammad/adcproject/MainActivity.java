package coding.mohammad.adcproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar ;

    CardView cardMusic, cardBrowse, cardTimer, cardStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setSupportActionBar(toolbar);


        cardBrowse.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,BrowseActivity.class)));

        cardMusic.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MusicActivity.class)));

        cardStore.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,UserActivity.class)));

        cardTimer.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,TimerActivity.class)));

    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        cardMusic = findViewById(R.id.card_music);
        cardBrowse = findViewById(R.id.card_browse);
        cardTimer = findViewById(R.id.card_timer);
        cardStore = findViewById(R.id.card_store);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_about:
                //
                showDialog("About", "ADC beginner project developed by Mohammad Reza Noorzade");
                return true;
            case R.id.item_setting:
                //
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog(String title, String message) {
        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setCancelText("close")
                .setConfirmText("github")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //
                        openLink("https://github.com/coding-mohammad");
                        sDialog.dismissWithAnimation();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    private void openLink(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }
}