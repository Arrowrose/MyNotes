package com.example.mynotes.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "image")
public class Image implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] imagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagePath() {
        return imagePath;
    }

    public void setImagePath(byte[] imagePath) {
        this.imagePath = imagePath;
    }
}
