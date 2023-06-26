package com.example.stelp;

import androidx.cardview.widget.CardView;

import com.example.stelp.Models.Notes;

public interface NotesClickListener {

    void onClick (Notes notes);
    void  onLongClick (Notes notes, CardView cardView);

}
