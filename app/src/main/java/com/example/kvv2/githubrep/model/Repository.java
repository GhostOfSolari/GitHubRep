package com.example.kvv2.githubrep.model;

import com.example.kvv2.githubrep.RouterInterface;
import com.example.kvv2.githubrep.model.tables.GitRepositoryTBL;

import java.util.List;

public class Repository implements RouterInterface.RepositoryInterface {

    private RouterInterface.RemoteRepositoryInterface mRemoteStorage;
    private RouterInterface.LocalRepositoryInterface mLocalStorage;
    private RouterInterface.OnGetData onGetData;
    private String mLastText = "";

    public Repository(RouterInterface.LocalRepositoryInterface localStorage, RouterInterface.RemoteRepositoryInterface remoteStorage) {
        mRemoteStorage = remoteStorage;
        mLocalStorage = localStorage;
    }

    @Override
    public void getData(String s, RouterInterface.OnGetData onGetData) {
        this.onGetData = onGetData;

        if (!s.equals(mLastText)) {
            mLastText = s;
            mRemoteStorage.getData(s, onRemoteStorageGetData);
        } else {
            mLocalStorage.getData(onLocalStorageGetData);
        }

    }

    private RouterInterface.OnGetData onRemoteStorageGetData = new RouterInterface.OnGetData() {
        @Override
        public void callBack(List<GitRepositoryTBL> listData, boolean isSuccessful) {
            if (listData.size() > 0) mLocalStorage.saveData(listData);
            onGetData.callBack(listData, isSuccessful);
        }
    };

    private RouterInterface.OnGetData onLocalStorageGetData = new RouterInterface.OnGetData() {
        @Override
        public void callBack(List<GitRepositoryTBL> listData, boolean isSuccessful) {
           onGetData.callBack(listData, isSuccessful);
        }
    };
}
