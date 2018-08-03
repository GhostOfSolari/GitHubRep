package com.example.kvv2.githubrep.StorageFiles.Interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.kvv2.githubrep.StorageFiles.Tables.GitRepositoryTBL;

import java.util.List;

@Dao
public interface RepositoryDao {

    @Query("SELECT * FROM GitRepositoryTBL")
    List<GitRepositoryTBL> getAll();

    @Insert
    void insert(List<GitRepositoryTBL> repository);

    @Query("DELETE FROM GitRepositoryTBL")
    void deleteAll();
}
