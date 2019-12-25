package com.hqgml.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import androidx.annotation.Nullable;


import static com.hqgml.chat.flash.client.NettyClient.startConsoleThread;

public class LoginActivity extends Activity {
    public EditText username;
    public EditText password;
    public Button loginbutton;
    public TextView regitst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = findViewById(R.id.loginusername);
        password = findViewById(R.id.loginpassword);
        regitst = findViewById(R.id.regist);
        loginbutton = findViewById(R.id.bt_login);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        regitst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,registActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

    }


    public void login() {
        startConsoleThread(username.getText().toString(), password.getText().toString());
    }


}



