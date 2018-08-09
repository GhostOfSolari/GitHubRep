package com.example.kvv2.githubrep.presenter;

import com.example.kvv2.githubrep.RouterInterface;
import com.example.kvv2.githubrep.model.tables.GitRepositoryTBL;

import java.util.List;

public class MainViewPresenter implements RouterInterface.MainViewPresenterInterface {

    private RouterInterface.RepositoryInterface mRepository;
    private RouterInterface.OnGetData onGetData;
    private boolean isCanceled = false;

    public MainViewPresenter(RouterInterface.RepositoryInterface repository) {
        mRepository = repository;
    }

    @Override
    public void getData(String s, RouterInterface.OnGetData onGetData) {
        isCanceled = false;
        this.onGetData = onGetData;
        mRepository.getData(s, onRepositoryGetData);
    }

    @Override
    public void cancel() {
        isCanceled = true;
    }

    private RouterInterface.OnGetData onRepositoryGetData = new RouterInterface.OnGetData() {
        @Override
        public void callBack(List<GitRepositoryTBL> listData, boolean isSuccessful) {
            if ((onGetData != null) && (!isCanceled)) {
                onGetData.callBack(listData, isSuccessful);
            }
        }
    };
}
