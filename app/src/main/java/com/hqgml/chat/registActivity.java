package com.hqgml.chat;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.hqgml.chat.pojo.User;
import com.hqgml.chat.utlis.OkHttpClientUtil;
import com.hqgml.chat.utlis.ToastUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class registActivity extends Activity {
    public EditText username;
    public EditText password;
    public EditText password2;
    public Button regits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        username = findViewById(R.id.registname);
        password = findViewById(R.id.registpassword);
        password2 = findViewById(R.id.registpassword2);
        regits = findViewById(R.id.resgitsbutton);
        regits.setOnClickListener(registOnclick);
    }

    private void regist() {
        String url = "http://www.hqgml.com:10086/user/regist";
        OkHttpClientUtil ok = new OkHttpClientUtil();
        Map<String, String> parm = new HashMap<>();
        parm.put("username", username.getText().toString());
        parm.put("password", password.getText().toString());
        ok.postDataAsync(url, parm, new OkHttpClientUtil.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                System.out.println(response.code());
                System.out.println(response.body().string());
                if (response.code() == 200) {
                    ToastUtils.showToast(registActivity.this, "注册成功");
                    Intent intent = new Intent(registActivity.this, LoginActivity.class);
                    registActivity.this.startActivity(intent);
                } else if (response.code()==401){
                    ToastUtils.showToast(registActivity.this, "该用户已经存在");
                }else {
                    ToastUtils.showToast(registActivity.this, "注册失败");
                }
            }
            @Override
            public void failed(Call call, IOException e) {
                ToastUtils.showToast(registActivity.this, "服务器异常");
            }
        });

    }

    private View.OnClickListener registOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String reg = "^(?![0-9])(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

            if (!password2.getText().toString().equals(password.getText().toString())) {
                ToastUtils.showToast(registActivity.this, "两次密码输入不一样");
                return;
            }


            if (username.getText().toString().equals("") || password2.getText().toString().equals("") ||
                    password.getText().toString().equals("")
            ) {
                ToastUtils.showToast(registActivity.this, "用户名以及密码不能为空");
                return;
            }

            if (!password.getText().toString().matches(reg)) {
                ToastUtils.showToast(registActivity.this, "密码必须为6-16位数字字母混合,不能全为数字,不能全为字母,首位不能为数字");
                return;
            }
            regist();
        }
    };
}
