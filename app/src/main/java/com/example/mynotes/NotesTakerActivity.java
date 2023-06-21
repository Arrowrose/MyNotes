package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynotes.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText editText_title, editText_notes;
    Button btn_save;
    ImageView imageView_menu, imageView_back;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);
        btn_save = findViewById(R.id.btn_save);

        imageView_menu = findViewById(R.id.imageView_menu);
        imageView_back = findViewById(R.id.imageView_back);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(notes.getTitle());
            editText_notes.setText(notes.getNotes());
            isOldNote = true;
        }catch (Exception e) {
            e.printStackTrace();
        }


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String description = editText_notes.getText().toString();


                if (description.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Please enter description", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                Date date = new Date();

                if (!isOldNote) {
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formater.format(date));

                Intent intent = new Intent();
                intent.putExtra("notes", notes);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (NotesTakerActivity.this, MainActivity.class);
                finish();
            }
        });
    }
}