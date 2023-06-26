package com.example.stelp.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.stelp.Models.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict = REPLACE)
    void insert (Notes notes);

    @Query("SELECT * FROM notes")
    List<Notes> getAll();

    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :ID")
    void update (int ID, String title, String notes);

    @Delete
    void delete (Notes notes);

    @Query("UPDATE notes SET pinned = :pin WHERE ID = :ID")
    void pin (int ID, boolean pin);
}
