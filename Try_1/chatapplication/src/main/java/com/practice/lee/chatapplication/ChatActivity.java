package com.practice.lee.chatapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText Input_Message;
    private Button bt_send;
    private LinearLayout message_log;
    private ScrollView sc_text;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Input_Message = (EditText) findViewById(R.id.input_message);
        bt_send = (Button) findViewById(R.id.bt_send);
        message_log = (LinearLayout) findViewById(R.id.message_log);
        sc_text = (ScrollView) findViewById(R.id.sc_text);
        bt_send.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        final int marginSize = getResources().getDimensionPixelSize(R.dimen.message_margin);
        TextView userMessage = new TextView(this);
        LinearLayout.LayoutParams userMssageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView cMessage = new TextView(this);
        final LinearLayout.LayoutParams cMssageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if(v.equals(bt_send)){
            String answer;
            String inputText = Input_Message.getText().toString();

            userMessage.setText(inputText);
            userMssageLayout.gravity = Gravity.END;
            userMssageLayout.setMargins(0, marginSize,0,marginSize);

            userMessage.setBackgroundResource(R.drawable.user_message);
            userMessage.setTextColor(Color.WHITE);
            message_log.addView(userMessage, LinearLayout.LayoutParams.WRAP_CONTENT, userMssageLayout);

            Input_Message.setText("");
            TranslateAnimation userMessageAnimation = new TranslateAnimation(message_log.getWidth(),0,0,0);
            userMessageAnimation.setDuration(500);
            userMessage.startAnimation(userMessageAnimation);

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

            cMessage.setText(answer);
            cMessage.setBackgroundResource(R.drawable.cpu_message);
            cMessage.setTextColor(Color.WHITE);
            userMessageAnimation.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    sc_text.fullScroll(View.FOCUS_DOWN);
                    cMssageLayout.gravity = Gravity.START;
                    cMssageLayout.setMargins(marginSize,marginSize,marginSize,marginSize);
                    message_log.addView(cMessage, LinearLayout.LayoutParams.WRAP_CONTENT, cMssageLayout);
                    TranslateAnimation cMessageAnimation = new TranslateAnimation(-message_log.getWidth(),0,0,0);
                    cMessageAnimation.setDuration(500);
                    cMessage.startAnimation(cMessageAnimation);
                    cMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {                     }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            sc_text.fullScroll(View.FOCUS_DOWN);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {                     }
                    });
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });

        }//bt_send
    }
}
