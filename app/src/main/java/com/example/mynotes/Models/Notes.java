package com.example.mynotes.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import java.io.Serializable;
//, foreignKeys = @ForeignKey(entity= User.class, parentColumns="userId", childColumns="userId", onDelete=ForeignKey.CASCADE)
@Entity (tableName = "notes")
public class Notes implements Serializable {


    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    //@ColumnInfo (name = "userId")
    //public
    //int userId;

    @ColumnInfo (name = "title")
    String title = "";

    @ColumnInfo (name = "notes")
    String notes = "";

    @ColumnInfo (name = "date")
    String date = "";

    @ColumnInfo (name = "pinned")
    Boolean pinned = false;

    @ColumnInfo(name = "isHighlighted")
    Boolean isHighlighted;

    public Boolean getHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(Boolean highlighted) {
        isHighlighted = highlighted;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getPinned() {
        return pinned;
    }


    //public int getUserId() {
       // return userId;
    //}

   // public void setUserId(int userId) {
       // this.userId = userId;
    //}

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

}

