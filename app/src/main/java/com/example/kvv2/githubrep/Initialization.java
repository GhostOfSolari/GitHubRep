package com.example.kvv2.githubrep;

import android.app.Application;

import com.example.kvv2.githubrep.model.LocalRepository;
import com.example.kvv2.githubrep.model.RemoteRepository;
import com.example.kvv2.githubrep.model.Repository;
import com.example.kvv2.githubrep.presenter.PresenterFactory;

public class Initialization extends Application {

    private RouterInterface.RepositoryInterface mRepository;
    private RouterInterface.LocalRepositoryInterface mLocalStorage;
    private RouterInterface.RemoteRepositoryInterface mRemoteStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        mLocalStorage = new LocalRepository(this);
        mRemoteStorage = new RemoteRepository();
        mRepository = new Repository(mLocalStorage, mRemoteStorage);
        PresenterFactory.initPresenterFactory(mRepository);

        // а можно и так зафигачить
        //PresenterFactory.initPresenterFactory(new Repository(new LocalRepository(this), new RemoteRepository()));
    }
}
