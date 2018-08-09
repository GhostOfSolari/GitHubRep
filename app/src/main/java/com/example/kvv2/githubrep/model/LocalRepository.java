package com.example.kvv2.githubrep.model;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.example.kvv2.githubrep.RouterInterface;
import com.example.kvv2.githubrep.model.db.StorageDB;
import com.example.kvv2.githubrep.model.tables.GitRepositoryTBL;

import java.util.List;

public class LocalRepository implements RouterInterface.LocalRepositoryInterface {

    private StorageDB database;
    private RouterInterface.OnGetData onGetData;

    public LocalRepository(Context context) {
        database = Room.databaseBuilder(context, StorageDB.class, "GitRepDB").build();
    }

    @Override
    public void saveData(List<GitRepositoryTBL> listData) {
        InsertDataTask mt = new InsertDataTask();
        mt.execute(listData);
    }

    @Override
    public void getData(RouterInterface.OnGetData onGetData) {
        this.onGetData = onGetData;
        GetDataTask mt = new GetDataTask();
        mt.execute();
    }

    private class GetDataTask extends AsyncTask<Void, Void, List<GitRepositoryTBL>> {

        @Override
        protected List<GitRepositoryTBL> doInBackground(Void... params) {
            return database.employeeDao().getAll();
        }

        @Override
        protected void onPostExecute(List<GitRepositoryTBL> result) {
            super.onPostExecute(result);

            if (onGetData != null) {
                onGetData.callBack(result, true);
            }
        }
    }

    private class InsertDataTask extends AsyncTask<List<GitRepositoryTBL>, Void, Void> {

        @Override
        protected Void doInBackground(List<GitRepositoryTBL>... lists) {
            database.employeeDao().deleteAll();
            database.employeeDao().insert(lists[0]);
            return null;
        }
    }
}
