package com.example.mynotes.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "profilePhoto", foreignKeys = @ForeignKey(entity= User.class, parentColumns="userId",
        childColumns="userId", onDelete=ForeignKey.CASCADE))
public class ProfilePhoto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    @ColumnInfo(name = "userId")
    public int userId;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] photoPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(byte[] photoPath) {
        this.photoPath = photoPath;
    }
}
