package com.example.kvv2.githubrep.model.tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "GitRepositoryTBL")
public class GitRepositoryTBL {

    @PrimaryKey(autoGenerate = true)
    public long kod;

    //public long id;

    public String full_name;

    @Override @Ignore
    public String toString() {
        return full_name;
    }
}
