package coding.mohammad.adcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BrowseActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarText;

    EditText edtSearch;
    ImageView imgSearch;
    WebView webView;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        findViews();

        setSupportActionBar(toolbar);

        toolbarText.setText("Browse");

        imgSearch.setOnClickListener(v->{
            url = edtSearch.getText().toString();

            if (!url.isEmpty()){
                webView.loadUrl(url);
            }

        });

    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        toolbarText = findViewById(R.id.toolbar_txt);
        edtSearch = findViewById(R.id.edt_search);
        imgSearch = findViewById(R.id.img_search);
        webView = findViewById(R.id.web_view);
    }
}