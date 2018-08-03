package com.example.kvv2.githubrep;

import android.content.Context;

import com.example.kvv2.githubrep.StorageFiles.LocalStorage;
import com.example.kvv2.githubrep.StorageFiles.RemoteStorage;
import com.example.kvv2.githubrep.StorageFiles.Tables.GitRepositoryTBL;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

import java.util.List;

public class Repository implements RouterInterface.RepositoryInterface {

    private RouterInterface.RemoteStorageInterface mRemoteStorage;
    private RouterInterface.LocalStorageInterface mLocalStorage;
    private RouterInterface.OnGetData onGetData;
    private String mLastText = "";

    public Repository(Context context) {
        mRemoteStorage = new RemoteStorage();
        mLocalStorage = new LocalStorage(context);
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
