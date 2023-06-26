package com.example.stelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stelp.DataBase.RoomDB;
import com.example.stelp.DataBase.UserDao;
import com.example.stelp.Models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    Button  edit_p, exit_btn;
    TextView login_p, mail_p;
    ImageView back_btn_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        login_p = findViewById(R.id.login_p);
        mail_p = findViewById(R.id.mail_p);

        back_btn_p = findViewById(R.id.back_btn_p);
        back_btn_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


        RoomDB database = RoomDB.getInstance(context);
        UserDao userDao = database.userDao();
        User user = userDao.getUserById();
        String login = user.getLogin();
        String mail = user.getMail();
        login_p.setText(login);
        mail_p.setText(mail);

        edit_p = findViewById(R.id.edit_p);
        edit_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditProfileActivity.class));
            }
        });

        exit_btn = findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.notes_list:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.googleD:
                        startActivity(new Intent(getApplicationContext(),GoogleDiskActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.yandexD:
                        startActivity(new Intent(getApplicationContext(),YandexDiskActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}