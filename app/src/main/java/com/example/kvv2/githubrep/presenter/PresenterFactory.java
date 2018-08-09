package com.example.kvv2.githubrep.presenter;

import com.example.kvv2.githubrep.RouterInterface;

public class PresenterFactory {

    private static PresenterFactory mInstance;
    private RouterInterface.RepositoryInterface mRepository;

    public static void initPresenterFactory(RouterInterface.RepositoryInterface repository) {

        if (mInstance == null)
            mInstance = new PresenterFactory();

        mInstance.mRepository = repository;
    }

    public static RouterInterface.MainViewPresenterInterface GetPresenter(RouterInterface.MainViewInterface view) {
        return new MainViewPresenter(mInstance.mRepository);
    }
}
