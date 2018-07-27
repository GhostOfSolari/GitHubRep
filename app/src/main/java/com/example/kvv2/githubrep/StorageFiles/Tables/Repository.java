package com.example.kvv2.githubrep.StorageFiles.Tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Repository {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String full_name;

    @Override @Ignore
    public String toString() {
        return full_name;
    }
}
