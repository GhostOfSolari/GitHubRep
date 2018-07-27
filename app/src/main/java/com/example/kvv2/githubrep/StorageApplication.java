package com.example.kvv2.githubrep;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import com.example.kvv2.githubrep.StorageFiles.StorageDB;
import com.example.kvv2.githubrep.StorageFiles.Tables.Repository;
import com.example.kvv2.githubrep.interfaces.RouterInterface;

import java.util.List;


public class StorageApplication extends Application implements RouterInterface.DBStorageInterface.StorageInterface {

    public static StorageApplication instance;
    private StorageDB database;
    private RouterInterface.DBStorageInterface.OnGetData onGetData;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, StorageDB.class, "database")
                .build();
    }

    public static StorageApplication getInstance() {
        return instance;
    }

    public StorageDB getDatabase() {
        return database;
    }

    @Override
    public void getData(RouterInterface.DBStorageInterface.OnGetData onGetData) {
        this.onGetData = onGetData;
        GetDataTask mt = new GetDataTask();
        mt.execute();
    }

    @Override
    public void saveData(List<Repository> listData) {
        InsertDataTask mt = new InsertDataTask();
        mt.execute(listData);
    }

    private class GetDataTask extends AsyncTask<Void, Void, List<Repository>> {

        @Override
        protected List<Repository> doInBackground(Void... params) {
            return getInstance().getDatabase().employeeDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Repository> result) {
            super.onPostExecute(result);

            if (onGetData != null) {
                onGetData.callBack(result);
            }
        }
    }

    private class InsertDataTask extends AsyncTask<List<Repository>, Void, Void> {

        @Override
        protected Void doInBackground(List<Repository>... lists) {
            getInstance().getDatabase().employeeDao().deleteAll();
            getInstance().getDatabase().employeeDao().insert(lists[0]);
            return null;
        }
    }
}
