package com.example.mynotes.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mynotes.Models.Image;
import com.example.mynotes.Models.NoteImage;
import com.example.mynotes.Models.Notes;
import com.example.mynotes.Models.ProfilePhoto;
import com.example.mynotes.Models.User;

@Database(entities = {Notes.class, User.class, ProfilePhoto.class, Image.class, NoteImage.class}, version = 3, exportSchema = false)
public abstract class RoomDB  extends RoomDatabase {

    private static RoomDB database;
    private static String DATABASE_NAME = "Stelp";

    public synchronized  static RoomDB getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract UserDao userDao();

    public abstract ProfilePhotoDao profilePhotoDao();

    public abstract  NotesDao notesDao();

    public abstract  ImageDao imageDao();

    public abstract NoteImageDao noteImageDao();
}
