package com.example.kvv2.githubrep.StorageFiles;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.kvv2.githubrep.StorageFiles.Interface.RepositoryDao;
import com.example.kvv2.githubrep.StorageFiles.Tables.Repository;

@Database(entities = {Repository.class}, version = 1)
public abstract class StorageDB extends RoomDatabase {
    public abstract RepositoryDao employeeDao();
}
