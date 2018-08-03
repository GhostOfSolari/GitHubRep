package com.example.kvv2.githubrep;

import com.example.kvv2.githubrep.StorageFiles.Tables.GitRepositoryTBL;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

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
    public void Cancel() {
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
