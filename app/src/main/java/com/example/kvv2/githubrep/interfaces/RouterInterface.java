package com.example.kvv2.githubrep.interfaces;

import com.example.kvv2.githubrep.StorageFiles.Tables.Repository;

import java.util.List;

public interface RouterInterface {

    interface MainViewInterface {

        void viewData(List<Repository> listData);
    }

    interface MainViewPresenterInterface {

        void getData(String s);
        void gitSearcherCallBack(List<Repository> listData);
        void Cancel();
    }

    interface DBStorageInterface {

        interface OnGetData {
            void callBack(List<Repository> listData);
        }

        interface StorageInterface {

            void saveData(List<Repository> listData);
            void getData(RouterInterface.DBStorageInterface.OnGetData onGetData);
        }
    }

    interface GitSearcherInterface {

        void search(String s);
    }
}
