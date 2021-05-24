package com.example.goodlife;

import android.util.Log;

import com.example.goodlife.wjh.MyListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectUtil {

    public static void connectByUrl(final String url, final MyListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .post(RequestBody.create(null, ""))
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        listener.onFailure("网络连接失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = response.body().string();
                        Log.e("str", str);
                        listener.onSuccess(str);
                    }
                });
            }
        }).start();
    }

    public static void connectByJson(final String url, final String json, final MyListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json;charset=utf-8"));
                Request request = new Request.Builder()
                        .post(requestBody)
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("Error:",e.getMessage());
                        listener.onFailure("网络连接失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        listener.onSuccess(str);
                    }
                });
            }
        }).start();
    }
}
