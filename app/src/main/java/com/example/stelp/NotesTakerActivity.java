package com.example.stelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stelp.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText editText_title, editText_notes;
    TextView dateTextView;
    ImageView imageView_menu, back_btn;
    Button save_btn;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        save_btn = findViewById(R.id.save_btn);

        imageView_menu = findViewById(R.id.imageView_menu);

        dateTextView = findViewById(R.id.dateTextView);

        Date today = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("y HH:mm     EEE, d MMM yyy");
        String formattedDate = dateFormat.format(today);

        // Установка отформатированной даты в TextView
        dateTextView.setText(formattedDate);

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesTakerActivity.this, MainActivity.class);
                finish();
            }
        });

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            //notes.setUserId(notes.userId);
            editText_title.setText(notes.getTitle());
            editText_notes.setText(notes.getNotes());
            isOldNote = true;
        }catch (Exception e) {
            e.printStackTrace();
        }


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String description = editText_notes.getText().toString();

                if (description.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Пожалуйста, введите текст", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                Date date = new Date();

                if (!isOldNote) {
                    notes = new Notes();
                }

                //notes.setUserId(notes.userId);
                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formater.format(date));

                Intent intent = new Intent();
                intent.putExtra("notes", notes);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }
}