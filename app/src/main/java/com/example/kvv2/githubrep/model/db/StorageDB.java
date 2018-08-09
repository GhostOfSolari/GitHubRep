package com.example.kvv2.githubrep.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.kvv2.githubrep.model.dao.RepositoryDao;
import com.example.kvv2.githubrep.model.tables.GitRepositoryTBL;

@Database(entities = {GitRepositoryTBL.class}, version = 1)
public abstract class StorageDB extends RoomDatabase {
    public abstract RepositoryDao employeeDao();
}
