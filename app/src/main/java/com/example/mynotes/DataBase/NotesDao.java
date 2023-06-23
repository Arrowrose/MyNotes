package com.example.mynotes.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mynotes.Models.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict = REPLACE)
    void insert (Notes notes);

    @Query("SELECT * FROM notes WHERE pinned = 'true' UNION SELECT * FROM notes ORDER BY pinned DESC, ID DESC")
    List<Notes> getAll();

    @Query("UPDATE notes SET title = :title, notes = :notes, userId = :userId WHERE ID = :ID")
    void update (int ID, int userId, String title, String notes);

    @Delete
    void delete (Notes notes);

    @Query("UPDATE notes SET pinned = :pin WHERE ID = :ID")
    void pin (int ID, boolean pin);
}
