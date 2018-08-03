package com.example.kvv2.githubrep.StorageFiles;

import android.util.Log;

import com.example.kvv2.githubrep.StorageFiles.Tables.GitRepositoryTBL;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RemoteStorage implements RouterInterface.RemoteStorageInterface {

    private final static String LOG_TAG = "myLogs";
    private RouterInterface.OnGetData onEnqueue;

    private class Message {

        private String total_count;
        private boolean incomplete_results;
        private List<GitRepositoryTBL> items;
    }

    private interface MessagesApi {

        //@GET("search/{name}")
        //Call<Message> messages(@Path(value="name", encoded=true) String name);
        //@GET("search/repositories?q=grit in:name")
        //Call<Message> messages();
        @GET("search/repositories")
        Call<Message> messages(@Query("q") String q);
    }

    @Override
    public void getData(String s, RouterInterface.OnGetData onGetData) {
        this.onEnqueue = onGetData;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MessagesApi messagesApi = retrofit.create(MessagesApi.class);

        //s = "repositories?q=" + s + " in:name";
        Call<Message> messages = messagesApi.messages(s);
        //Call<Message> messages = messagesApi.messages();

        messages.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                onEnqueue.callBack(response.body().items, response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d(LOG_TAG, t.getMessage());
                onEnqueue.callBack(null, false);
            }
        });
    }
}
