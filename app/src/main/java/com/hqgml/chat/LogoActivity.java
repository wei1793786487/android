package com.hqgml.chat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class LogoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 获取actionbar
         */
        ActionBar ActionBar = getSupportActionBar();
        assert ActionBar != null;
        ActionBar.hide();
        setContentView(R.layout.logo);
        Button viewById = findViewById(R.id.loginbutton);
//        NettyClient mssss =new NettyClient();
//        mssss.main(this);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
