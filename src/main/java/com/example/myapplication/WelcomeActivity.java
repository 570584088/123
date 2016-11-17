package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY,3000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Jump();
            }
        },3000);


    }

    private void Jump() {
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
        finish();
    }
//    protected final int GOTO_MAIN_ACTIVITY = 0;
//    Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message message) {
//            switch (message.what) {
//                case GOTO_MAIN_ACTIVITY:
//                    Intent intent = new Intent();
//                    intent.setClass(WelcomeActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//                default:
//                    break;
//            }
//            return true;
//        }
//    });


}
