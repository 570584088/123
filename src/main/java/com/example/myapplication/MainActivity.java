package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.fragment.expert;
import com.example.myapplication.fragment.illtest;
import com.example.myapplication.fragment.news;
import com.example.myapplication.fragment.personal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup group;
    private ArrayList<Fragment> frags = new ArrayList<Fragment>();
    private FragmentManager manager;

    private Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        initFragments();

        initView();

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fl,frags.get(0));
        ft.commit();

        lastFragment = frags.get(0);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton rb = (RadioButton) findViewById(i);
                int tag = Integer.parseInt(rb.getTag().toString());

                if (!frags.get(tag).isAdded()) {
                    manager.beginTransaction().add(R.id.fl,frags.get(tag)).commit();
                } else {
                    manager.beginTransaction().show(frags.get(tag)).commit();
                }
                manager.beginTransaction().hide(lastFragment).commit();
                lastFragment = frags.get(tag);
            }
        });
    }

    private void initView() {
        group = (RadioGroup) findViewById(R.id.RadioGroup);
    }

    private void initFragments() {
        frags.add(new expert());
        frags.add(new illtest());
        frags.add(new news());
        frags.add(new personal());
    }


}
