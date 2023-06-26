package com.example.stelp.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.stelp.Models.Image;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert(onConflict = REPLACE)
    void insert (Image image);

    @Query("SELECT * FROM image ORDER BY id DESC")
    List<Image> getAll();

    @Query("UPDATE image SET imagePath = :imagePath WHERE id = :id")
    void update (int id, byte[] imagePath);

    @Delete
    void delete (Image image);
}
