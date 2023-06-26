package com.example.stelp.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.stelp.Models.NoteImage;

import java.util.List;

@Dao
public interface NoteImageDao {

    @Insert(onConflict = REPLACE)
    void insert (NoteImage noteImage);

    @Query("SELECT * FROM note_images ORDER BY note_id DESC")
    List<NoteImage> getAll();

    @Query("UPDATE note_images SET note_id = :note_id, image_id = :image_id WHERE note_id = :note_id")
    void update (int note_id, int image_id);

    @Delete
    void delete (NoteImage noteImage);

}
