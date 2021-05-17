package com.example.goodlife.wjh.regist.model;


import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterModel implements Model {
    @Override
    public void register(final String url, final RegisterListener registerListener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .get()
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        registerListener.onFailure("网络连接失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = response.body().string();
                        Log.e("str", str);

                        if(str.equals("error")){
                            registerListener.onFailure("注册失败");
                        }else{
                            registerListener.onSuccess(str);
                        }
                    }
                });
            }
        }).start();
    }
}