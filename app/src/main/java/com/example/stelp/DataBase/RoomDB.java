package com.example.stelp.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.stelp.Models.Image;
import com.example.stelp.Models.NoteImage;
import com.example.stelp.Models.Notes;
import com.example.stelp.Models.ProfilePhoto;
import com.example.stelp.Models.User;

@Database(entities = {Notes.class, User.class, ProfilePhoto.class, Image.class, NoteImage.class}, version = 1, exportSchema = false)
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

    public abstract  NotesDao notesDao();

    //public abstract ProfilePhotoDao profilePhotoDao();

   // public abstract  ImageDao imageDao();

    //public abstract NoteImageDao noteImageDao();

    //public Object getSerializableExtra(String users) {
        //return null;
   // }
}
