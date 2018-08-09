package com.example.kvv2.githubrep;

import com.example.kvv2.githubrep.model.tables.GitRepositoryTBL;

import java.util.List;

public interface RouterInterface {

    interface MainViewInterface {

    }

    interface OnGetData {
        void callBack(List<GitRepositoryTBL> listData, boolean isSuccessful);
    }

    interface MainViewPresenterInterface {

        void getData(String s, OnGetData onGetData);
        void cancel();
    }

    interface RepositoryInterface {
        void getData(String s, OnGetData onGetData);
    }

    interface RemoteRepositoryInterface {
        void getData(String s, OnGetData onGetData);
    }

    interface LocalRepositoryInterface {

        void saveData(List<GitRepositoryTBL> listData);
        void getData(OnGetData onGetData);
    }
}
