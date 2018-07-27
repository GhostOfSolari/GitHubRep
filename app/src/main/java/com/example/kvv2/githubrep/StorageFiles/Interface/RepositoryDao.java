package com.example.kvv2.githubrep.StorageFiles.Interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.kvv2.githubrep.StorageFiles.Tables.Repository;

import java.util.List;

@Dao
public interface RepositoryDao {

    @Query("SELECT * FROM repository")
    List<Repository> getAll();

    @Insert
    void insert(List<Repository> repository);

    @Query("DELETE FROM repository")
    void deleteAll();
}
