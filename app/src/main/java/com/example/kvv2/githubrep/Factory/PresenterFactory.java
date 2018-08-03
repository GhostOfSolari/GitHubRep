package com.example.kvv2.githubrep.Factory;

import android.app.Application;

import com.example.kvv2.githubrep.MainViewPresenter;
import com.example.kvv2.githubrep.Repository;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

public class PresenterFactory extends Application {

    private static PresenterFactory instance;
    private RouterInterface.RepositoryInterface mRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        instance.mRepository = new Repository(this);
    }

    public static RouterInterface.MainViewPresenterInterface GetPresenter(RouterInterface.MainViewInterface view) {
        return new MainViewPresenter(instance.mRepository);
    }
}
