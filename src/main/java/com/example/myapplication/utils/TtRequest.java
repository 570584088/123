package com.example.myapplication.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.bean.TtBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by 郭仲凯 on 2016/11/14.
 */

public class TtRequest extends Request<ArrayList<TtBean>> {

    private Response.Listener<ArrayList<TtBean>> listener;

    public TtRequest(String url, Response.Listener <ArrayList<TtBean>> listener,Response.ErrorListener errorListener) {
        this(Method.GET,url,listener,errorListener);
    }


    public TtRequest(int method, String url, Response.Listener<ArrayList<TtBean>> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;


    }

    private Map<String, String> map;

    public TtRequest(int method, String url, Map<String, String> map, Response.Listener<ArrayList<TtBean>> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        this.map = map;
    }

    @Override
    protected Response<ArrayList<TtBean>> parseNetworkResponse(NetworkResponse response) {

        String json;
        try {
            json = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            json = new String(response.data);
        }

        try {
            JSONObject jo = new JSONObject(json);
            JSONObject jo2 = jo.getJSONObject("data");
            JSONArray ja = jo2.getJSONArray("list");

            ArrayList<TtBean> list = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject obj = ja.getJSONObject(i);
                TtBean tb = new TtBean();
                tb.setTitle(obj.getString("title"));
                tb.setBriefinfo(obj.getString("briefinfo"));
                tb.setSmallimg(obj.getString("smallimg"));
                tb.setImgurl(obj.getString("imgurl"));
                tb.setTime(obj.getString("time"));
                tb.setUrl(obj.getString("url"));
                tb.setCategoryid(obj.getString("categoryid"));

                list.add(tb);
            }
            return Response.success(list,HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(ArrayList<TtBean> response) {
        if (listener != null){
            listener.onResponse(response);
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}