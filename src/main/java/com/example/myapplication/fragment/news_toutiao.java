package com.example.myapplication.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.bean.TtBean;
import com.example.myapplication.utils.TtRequest;
import com.example.myapplication.widght.NewsRv;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.myapplication.R.id.fl;

/**
 * Created by 郭仲凯 on 2016/11/14.
 */

public class news_toutiao extends Fragment{

    private XRecyclerView mRv;
    private ArrayList<TtBean> list = new ArrayList<>();
    private MyAdapter adapter;

    private ImageLoader loader;

    private Handler handler = new Handler();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_news_tuotiao,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        loader = ImageLoader.getInstance();

        loader.init(ImageLoaderConfiguration.createDefault(getActivity()));

        initData();

        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        initAdapter();

        mRv.addItemDecoration(new NewsRv(getActivity(),NewsRv.VERTICAL_LIST));

        mRv.setPullRefreshEnabled(true);

        mRv.setLoadingMoreEnabled(true);

        mRv.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        //下拉动画
        mRv.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);

        mRv.setArrowImageView(R.mipmap.pulltorefresh_down_arrow);

        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        mRv.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RequestQueue queue = Volley.newRequestQueue(getActivity());
                        for (int i = 2; i < 11; i++) {
                            TtRequest tr = new TtRequest("http://api.club.xywy.com/mobile_app_article.php?act=article_list&first=1&page=" + i + "&pagesize=10&sign=a943ddda6830cba5907ec7c8170645f3",
                                    new Response.Listener<ArrayList<TtBean>>() {
                                        @Override
                                        public void onResponse(ArrayList<TtBean> response) {
                                            list.addAll(response);
                                        }
                                    },null);
                            queue.add(tr);
                        }
                        adapter.notifyDataSetChanged();
                        mRv.loadMoreComplete();
                    }
                },3000);
            }
        });
    }

    private void initAdapter() {
        adapter = new MyAdapter();
        mRv.setAdapter(adapter);
    }

    private void initData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        TtRequest tr = new TtRequest("http://api.club.xywy.com/mobile_app_article.php?act=article_list&first=1&page=1&pagesize=10&sign=a943ddda6830cba5907ec7c8170645f3",
                new Response.Listener<ArrayList<TtBean>>() {
                    @Override
                    public void onResponse(ArrayList<TtBean> response) {
                        Log.i("tmd", "onResponse: "+response.toString());
                        list.addAll(response);
                        adapter.notifyDataSetChanged();
                    }
                },null);
        queue.add(tr);
    }

    private void initView(View v) {
        mRv = (XRecyclerView) v.findViewById(R.id.xrv);
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public int getItemViewType(int position) {
//            String categoryid = list.get(position).getCategoryid();
            if (position==0) {
                return 1;
            } else {
                return 2;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder holder = null;
            switch (viewType) {
                case 1:
                    holder = new item2(LayoutInflater.from(getActivity()).inflate(R.layout.activity_news_item2,parent,false));
                    break;
                case 2:
                    holder = new item(LayoutInflater.from(getActivity()).inflate(R.layout.activity_news_item,parent,false));
                    break;
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TtBean tb = list.get(position);
            switch (getItemViewType(position)) {
                case 1:
                    final item2 i2 = (item2) holder;
//                    loader.displayImage(tb.getImgurl(),i2.rv_iv2);
                    i2.rv_tx4.setText(tb.getBriefinfo());

                    i2.rv_iv2.setTag(tb.getImgurl());
                    i2.rv_iv2.setImageResource(R.mipmap.loadding_question);

                    loader.loadImage(tb.getImgurl(), new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            if (i2.rv_iv2.getTag() != null && i2.rv_iv2.getTag().toString().equals(imageUri)) {
                                i2.rv_iv2.setImageBitmap(loadedImage);
                            }
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });
                    break;
                case 2:
                    final item i1 = (item) holder;
//                    loader.displayImage(tb.getSmallimg(),i1.rv_iv1);
                    i1.rv_tv1.setText(tb.getTitle());
                    i1.rv_tv2.setText(tb.getBriefinfo());
                    i1.rv_tv3.setText(tb.getTime());

                    i1.rv_iv1.setTag(tb.getSmallimg());
                    i1.rv_iv1.setImageResource(R.mipmap.loadding_question);

                    loader.loadImage(tb.getSmallimg(), new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            if (i1.rv_iv1.getTag() != null && i1.rv_iv1.getTag().toString().equals(imageUri))
                                i1.rv_iv1.setImageBitmap(loadedImage);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {

                        }
                    });
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class item extends RecyclerView.ViewHolder{

            ImageView rv_iv1;
            TextView rv_tv1;
            TextView rv_tv2;
            TextView rv_tv3;

            public item(View itemView) {
                super(itemView);
                rv_iv1 = (ImageView) itemView.findViewById(R.id.item_iv1);
                rv_tv1 = (TextView) itemView.findViewById(R.id.item_tv1);
                rv_tv2 = (TextView) itemView.findViewById(R.id.item_tv2);
                rv_tv3 = (TextView) itemView.findViewById(R.id.item_tv3);
            }
        }

        class item2 extends RecyclerView.ViewHolder{

            ImageView rv_iv2;
            TextView rv_tx4;
            public item2(View itemView) {
                super(itemView);
                rv_iv2 = (ImageView) itemView.findViewById(R.id.toutiao_iv1);
                rv_tx4 = (TextView) itemView.findViewById(R.id.toutiao_tv1);
            }
        }
    }
}
