package com.example.kvv2.githubrep;

import android.util.Log;

import com.example.kvv2.githubrep.StorageFiles.Tables.Repository;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

import java.util.List;

public class MainViewPresenter implements RouterInterface.MainViewPresenterInterface {


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
        //mView.viewData(null);
    }

    @Override
    public void getData(String s) {
        isCanceled = false;

        if (!s.trim().equals(mLastText.trim())) {
            Log.d(String.valueOf(R.string.log_tag), "SEARCH...");
            mLastText = s;
            mSearcher.search(s);
        } else {
            Log.d(String.valueOf(R.string.log_tag), "FROM DB...");
            StorageApplication.getInstance().getData(onGetData);
        }
    }

    @Override
    public void gitSearcherCallBack(List<Repository> listData, boolean isSuccessful) {
        if (isCanceled) return;

        if (!isCanceled && listData.size() > 0)
            StorageApplication.getInstance().saveData(listData);

        mView.viewData(listData, isSuccessful);
    }

    private RouterInterface.DBStorageInterface.OnGetData onGetData = new RouterInterface.DBStorageInterface.OnGetData() {
        @Override
        public void callBack(List<Repository> listData) {
            if (isCanceled) return;
            mView.viewData(listData, true);
        }
    };
}
