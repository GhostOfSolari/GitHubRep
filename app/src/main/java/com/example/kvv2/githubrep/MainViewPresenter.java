package com.example.kvv2.githubrep;

import android.util.Log;

import com.example.kvv2.githubrep.StorageFiles.Tables.Repository;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

import java.util.List;

public class MainViewPresenter implements RouterInterface.MainViewPresenterInterface {

    final String LOG_TAG = "myLogs";

    private RouterInterface.MainViewInterface mView;
    private RouterInterface.GitSearcherInterface mSearcher;
    private String mLastText = "";
    private boolean isCanceled = false;

    public MainViewPresenter(RouterInterface.MainViewInterface aView) {
        this.mView = aView;
        this.mSearcher = new GitSearcher(this);
    }

    @Override
    public void Cancel() {
        isCanceled = true;
        mLastText = "";
        mView.viewData(null);
    }

    @Override
    public void getData(String s) {
        isCanceled = false;

        if (!s.trim().equals(mLastText.trim())) {
            Log.d(LOG_TAG, "SEARCH...");
            mLastText = s;
            mSearcher.search(s);
        } else {
            Log.d(LOG_TAG, "FROM DB...");
            StorageApplication.getInstance().getData(onGetData);
        }
    }

    @Override
    public void gitSearcherCallBack(List<Repository> listData) {
        if (isCanceled) return;

        if (listData.size() > 0)
            StorageApplication.getInstance().saveData(listData);

        mView.viewData(listData);
    }

    private RouterInterface.DBStorageInterface.OnGetData onGetData = new RouterInterface.DBStorageInterface.OnGetData() {
        @Override
        public void callBack(List<Repository> listData) {
            if (isCanceled) return;
            mView.viewData(listData);
        }
    };
}
