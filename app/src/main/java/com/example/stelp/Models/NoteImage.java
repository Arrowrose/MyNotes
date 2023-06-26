package com.example.stelp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "note_images", primaryKeys = {"note_id", "image_id" },
foreignKeys = {@ForeignKey(entity = Notes.class, parentColumns = "ID", childColumns = "note_id", onDelete = ForeignKey.CASCADE),
@ForeignKey(entity = Image.class, parentColumns = "id", childColumns = "image_id", onDelete = ForeignKey.CASCADE)})
public class NoteImage implements Serializable {

    @ColumnInfo(name = "note_id")
    public  int note_id;

    @ColumnInfo(name = "image_id")
    public  int image_id;

    public NoteImage(int note_id, int image_id) {
        this.note_id = note_id;
        this.image_id = image_id;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
