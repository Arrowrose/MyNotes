package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mynotes.Adapter.NotesListAdapter;
import com.example.mynotes.DataBase.RoomDB;
import com.example.mynotes.Models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RecyclerView recyclerView;
    Button menu_btn;
    FloatingActionButton fab_add;
    NotesListAdapter notesListAdapter;
    NavigationView nav_view;
    RoomDB database;
    List<Notes> notes = new ArrayList<>();
    SearchView searchView_home;
    Notes selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout myDrawerLayout = findViewById(R.id.drawer_layout_id);

        menu_btn = findViewById(R.id.menu_btn);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(!myDrawerLayout.isDrawerOpen(Gravity.START)) myDrawerLayout.openDrawer(Gravity.START);
                else myDrawerLayout.closeDrawer(Gravity.END);

            }
        });

        nav_view = findViewById(R.id.nav_view_id);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.profile_btn:
                        Intent intent_p = new Intent(MainActivity.this, ProfileActivity.class);
                        Toast.makeText(MainActivity.this, "Профиль", Toast.LENGTH_SHORT).show();
                        myDrawerLayout.closeDrawer(Gravity.END);
                        return  true;
                    case R.id.googleD_btn:
                        Intent intent_g = new Intent(MainActivity.this, GoogleDiskActivity.class);
                        Toast.makeText(MainActivity.this, "Google диск", Toast.LENGTH_SHORT).show();
                        myDrawerLayout.closeDrawer(Gravity.END);
                        return  true;
                    case R.id.yandexD_btn:
                        Intent intent_y = new Intent(MainActivity.this, YandexDiskActivity.class);
                        Toast.makeText(MainActivity.this, "Yandex диск", Toast.LENGTH_SHORT).show();
                        myDrawerLayout.closeDrawer(Gravity.END);
                        return  true;
                    case R.id.notes_btn:
                        Intent intent_n = new Intent(MainActivity.this, MainActivity.class);
                        Toast.makeText(MainActivity.this, "Заметки", Toast.LENGTH_SHORT).show();
                        myDrawerLayout.closeDrawer(Gravity.END);
                        return  true;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        searchView_home = findViewById(R.id.searchView_home);

        database = RoomDB.getInstance(this);
        notes = database.mainDAO().getAll();

        updateRecycler(notes);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter (newText);
                return true;
            }
        });
    }

    private void filter(String newText) {
        List<Notes> filteredList = new ArrayList<>();
        for (Notes singleNote : notes) {
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
            || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase()) ) {
                filteredList.add(singleNote);
            }
        }
        notesListAdapter.filterList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                Notes new_notes = (Notes) data.getSerializableExtra("notes");
                database.mainDAO().insert(new_notes);
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK) {
                Notes new_notes = (Notes) data.getSerializableExtra("notes");
                database.mainDAO().update(new_notes.getID(), new_notes.getTitle(), new_notes.getNotes());
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(MainActivity.this,notes,notesClickListener);
        recyclerView.setAdapter(notesListAdapter);
    }

    private final  NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes notes) {
            Intent intent = new Intent (MainActivity.this, NotesTakerActivity.class);
            intent.putExtra("old_note", notes);
            startActivityForResult(intent, 102);
        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            selectedNote = new Notes();
            selectedNote = notes;
            showPopUp (cardView);
        }
    };

    private void showPopUp(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pin:
                if (selectedNote.getPinned()) {
                    database.mainDAO().pin(selectedNote.getID(), false);
                    Toast.makeText(MainActivity.this, "Unpinned", Toast.LENGTH_SHORT).show();
                } else {
                    database.mainDAO().pin(selectedNote.getID(), true);
                    Toast.makeText(MainActivity.this, "Pinned", Toast.LENGTH_SHORT).show();
                }
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
                return true;

            case R.id.delete:
                database.mainDAO().delete(selectedNote);
                notes.remove(selectedNote);
                notesListAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Note removed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}