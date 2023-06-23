package com.example.mynotes.DataBase;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.mynotes.Models.ProfilePhoto;

import java.util.List;

@Dao
public interface ProfilePhotoDao {

    @Insert(onConflict = REPLACE)
    void insert (ProfilePhoto profilePhoto);

    @Query("SELECT * FROM profilePhoto ORDER BY  id")
    List<ProfilePhoto> getAll();

    @Query("UPDATE profilePhoto SET userId = :userId, photoPath = :photoPath WHERE id = :id")
    void update (int id, int userId, byte[] photoPath);

    @Delete
    void delete (ProfilePhoto notes);

}
