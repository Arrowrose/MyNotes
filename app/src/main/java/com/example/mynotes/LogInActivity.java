package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.DataBase.RoomDB;
import com.example.mynotes.DataBase.UserDao;
import com.example.mynotes.Models.User;

public class LogInActivity extends AppCompatActivity {

    EditText login, password;
    Button log_btn, reg_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        log_btn = findViewById(R.id.log_btn);

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginText = login.getText().toString();
                String passwordText = password.getText().toString();
                String mailText = login.getText().toString();
                if (loginText.isEmpty() || passwordText.isEmpty() || mailText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    RoomDB database = RoomDB.getInstance(getApplicationContext());
                    UserDao userDao = database.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.login(loginText, passwordText, mailText);
                            if (user == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Неверные учётные данные", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {

                                startActivity(new Intent(
                                        LogInActivity.this, MainActivity.class));


                            }
                        }
                    }).start();
                }
            }
        });

        reg_btn = findViewById(R.id.reg_btn);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        LogInActivity.this, RegistrationActivity.class));
            }
        });
    }
}