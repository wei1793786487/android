package com.hqgml.chat.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hqgml.chat.R;
import com.hqgml.chat.adapter.MsgAdapter;
import com.hqgml.chat.pojo.Msg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ActionBar ActionBar = getSupportActionBar();
        ActionBar.setTitle("与小明的聊天");
        ActionBar.setLogo(R.mipmap.logo);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatmian);

        initMsgs();
        inputText = findViewById(R.id.input_txet);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();

                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息，刷新RecyclerVeiw的显示
                    adapter.notifyItemInserted(msgList.size() - 1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    //清空输入框内容
                    inputText.setText("");

                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("你好哇，李银河", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hi", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg31 = new Msg("远远想不", Msg.TYPE_SENT);
        msgList.add(msg31);
        Msg msg3 = new Msg("当我跨过沉沦到一切",
                Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
