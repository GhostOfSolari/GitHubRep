package com.example.kvv2.githubrep;

import com.example.kvv2.githubrep.StorageFiles.Tables.Repository;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class GitSearcher implements RouterInterface.GitSearcherInterface {

    private RouterInterface.MainViewPresenterInterface mPresenter;


    private class Message {

        private String total_count;
        private boolean incomplete_results;
        private List<Repository> items;
    }

    private interface MessagesApi {

        //@GET("search/{name}")
        //Call<Message> messages(@Path(value="name", encoded=true) String name);
        //@GET("search/repositories?q=grit in:name")
        //Call<Message> messages();
        @GET("search/repositories")
        Call<Message> messages(@Query("q") String q);
    }

    public GitSearcher(RouterInterface.MainViewPresenterInterface aPresenter) {
        this.mPresenter = aPresenter;
    }

    @Override
    public void search(String s) {
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
                if (response.isSuccessful()) {
                    mPresenter.gitSearcherCallBack((List<Repository>)response.body().items);
                } else {

                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}
