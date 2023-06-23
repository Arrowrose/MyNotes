package com.example.mynotes.DataBase;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.Models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser (User user);

    @Query("SELECT * FROM users ORDER BY id DESC")
    List<User> getAll();

    @Query("UPDATE users SET login = :login, password = :password, mail = :mail WHERE id = :id")
    void update (int id, String login, String password, String mail);

    @Delete
    void delete (User user);
}
