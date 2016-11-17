package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by 郭仲凯 on 2016/11/11.
 */

public class news extends Fragment{

    private TabLayout tabs;
    private ViewPager viewPager;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_news,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        NewsAdapter adapter = new NewsAdapter(getFragmentManager(),mTitle,mFragment);
        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);
        tabs.setTabsFromPagerAdapter(adapter);
    }

    private void initView(View v) {
        tabs = (TabLayout) v.findViewById(R.id.tablayout);
        viewPager = (ViewPager) v.findViewById(R.id.pager);

        mTitle.add("头条");
        mTitle.add("两性");
        mTitle.add("母婴");
        mTitle.add("减肥");
        mTitle.add("美容");

        mFragment.add(new news_toutiao());
        mFragment.add(new news_lx());
        mFragment.add(new news_muying());
        mFragment.add(new news_jianfei());
        mFragment.add(new news_meiRong());
    }
}
