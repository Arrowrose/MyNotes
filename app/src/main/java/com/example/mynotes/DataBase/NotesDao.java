package com.example.mynotes.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.Models.User;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict = REPLACE)
    void insert (Notes notes);

    @Update
    void updateHighlight(Notes notes);


    //@Query("SELECT * FROM notes WHERE pinned = 'true' AND userId = (:userId) UNION SELECT * FROM notes WHERE userId = (:userId) ORDER BY pinned DESC, ID DESC")
   //void getAll(User userId);

    @Query("SELECT * FROM notes")
    List<Notes> getAll();

    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :ID")
    void update (int ID, String title, String notes);

    @Delete
    void delete (Notes notes);

    @Query("UPDATE notes SET pinned = :pin WHERE ID = :ID")
    void pin (int ID, boolean pin);


}
