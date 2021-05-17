package com.example.goodlife.wjh.login.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel implements Model {

    @Override
    public void login(final String url , final LoginListener loginListener) {
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
                        loginListener.onFailure("网络连接失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = response.body().string();
                        Log.e("str", str);

                        if(str.equals("usernameError")){
                            loginListener.onFailure("请输入正确的用户名");
                        }else if(str.equals("passwordError")){
                            loginListener.onFailure("密码错误");
                        }else{
                            loginListener.onSuccess(str);
                        }
                    }
                });
            }
        }).start();
    }
}
