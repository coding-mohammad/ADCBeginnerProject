package coding.mohammad.adcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbarText, txtUserInfo;
    EditText edtUserName, edtUserPass;
    Button btnLog;

    SharedPreferences sharedData ;

    boolean isUserLoggedIn;

    String userName,userPass;
    String info = "Your are logged in\nAccount info:\n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViews();

        setSupportActionBar(toolbar);
        toolbarText.setText(getString(R.string.toolbar_data_store));

        sharedData = getSharedPreferences("ADCProjectPreferences", Context.MODE_PRIVATE);

        isUserLoggedIn = sharedData.getBoolean("userLoggedIn", false);

        if (isUserLoggedIn){
            userLoggedIn();
        }else{
            userNotLoggedIn();
        }

        btnLog.setOnClickListener(v -> {
            isUserLoggedIn = sharedData.getBoolean("userLoggedIn", false);
            if (isUserLoggedIn){
                // logout
                SharedPreferences.Editor editor = sharedData.edit();
                editor.clear();
                editor.apply();
                userNotLoggedIn();
            }else{
                // login
                userName = edtUserName.getText().toString();
                userPass = edtUserPass.getText().toString();
                if (userName.trim().isEmpty()||userPass.trim().isEmpty()){
                    Toast.makeText(this, "fields can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor = sharedData.edit();
                    editor.putString("userName",userName);
                    editor.putString("userPass",userPass);
                    editor.putBoolean("userLoggedIn",true);
                    editor.apply();
                    userLoggedIn();
                }
            }
        });


    }

    private void findViews(){
        toolbar = findViewById(R.id.toolbar);
        toolbarText = findViewById(R.id.toolbar_txt);
        txtUserInfo = findViewById(R.id.txt_user_info);
        btnLog = findViewById(R.id.btn_log);
        edtUserName = findViewById(R.id.edt_user_name);
        edtUserPass = findViewById(R.id.edt_user_pass);
    }

    private void userLoggedIn(){
        userName = sharedData.getString("userName", "");
        userPass = sharedData.getString("userPass", "");
        info += "username : "+userName+"\npassword : "+userPass;

        edtUserName.setVisibility(View.GONE);
        edtUserPass.setVisibility(View.GONE);
        txtUserInfo.setVisibility(View.VISIBLE);
        txtUserInfo.setText(info);
        btnLog.setText("logout");
        btnLog.setBackgroundColor(Color.parseColor("#89000E"));
    }

    private void userNotLoggedIn(){
        edtUserName.setVisibility(View.VISIBLE);
        edtUserPass.setVisibility(View.VISIBLE);
        txtUserInfo.setVisibility(View.GONE);
        btnLog.setText("login");
        btnLog.setBackgroundColor(Color.parseColor("#3700B3"));
    }

}