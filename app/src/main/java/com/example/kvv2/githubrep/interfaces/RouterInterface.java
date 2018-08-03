package com.example.kvv2.githubrep.interfaces;

import com.example.kvv2.githubrep.StorageFiles.Tables.GitRepositoryTBL;

import java.util.List;

public interface RouterInterface {

    interface MainViewInterface {

    }

    interface OnGetData {
        void callBack(List<GitRepositoryTBL> listData, boolean isSuccessful);
    }

    interface MainViewPresenterInterface {

        void getData(String s, OnGetData onGetData);
        void Cancel();
    }

    interface RepositoryInterface {
        void getData(String s, OnGetData onGetData);
    }

    interface RemoteStorageInterface {
        void getData(String s, OnGetData onGetData);
    }

    interface LocalStorageInterface {

        void saveData(List<GitRepositoryTBL> listData);
        void getData(OnGetData onGetData);
    }
}
