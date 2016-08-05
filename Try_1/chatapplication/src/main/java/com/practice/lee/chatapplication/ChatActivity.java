package com.practice.lee.chatapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText Input_Message;
    private Button bt_send;
    private LinearLayout message_log;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Input_Message = (EditText) findViewById(R.id.input_message);
        bt_send = (Button) findViewById(R.id.bt_send);
        bt_send.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.equals(bt_send)){
            String inputText = Input_Message.getText().toString();
            String answer;

            TextView userMessage = new TextView(this);
            userMessage.setText(inputText);
            userMessage.setGravity(Gravity.END);
            message_log.addView(userMessage,0);

            if(inputText.contains("안녕")){
                answer="안녕하세요";
            }else if(inputText.contains("운세")){
                double random = Math.random()*5d;
                if(random<1d){
                    answer="몹시 나쁨";
                }else if(random<2d){
                    answer="나쁨";
                }else if(random<3d){
                    answer="보통";
                }else if(random<4d){
                    answer="행운";
                }else{
                    answer="대박";
                }
            }else if(inputText.contains("시간")){
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                answer = String.format("현재 시각은 %1$d시 %2$d분 %3$d초입니다.",hour, minute, second);
            }else{
                answer = "할말 없음.";
            }

            TextView cMessage = new TextView(this);
            cMessage.setText(answer);
            cMessage.setGravity(Gravity.START);
            message_log.addView(cMessage,0);

        }//bt_send
    }
}
