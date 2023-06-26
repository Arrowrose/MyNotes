package com.example.stelp.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.stelp.Models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM users ORDER BY userId DESC")
    List<User> getAll();

    @Query("SELECT * FROM users LIMIT 100")
    User getUserById();

    @Query("SELECT * FROM users WHERE login = (:login) AND password = (:password)  UNION SELECT * FROM users WHERE mail = (:mail) AND password  - (:password)")
    User login(String login, String password, String mail);

    @Query("UPDATE users SET login = :login, password = :password, mail = :mail WHERE userId = :userId")
    void update (int userId, String login, String password, String mail);

    @Delete
    void delete (User user);




}
