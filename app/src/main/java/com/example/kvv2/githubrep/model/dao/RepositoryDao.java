package com.example.kvv2.githubrep.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.kvv2.githubrep.model.tables.GitRepositoryTBL;

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
