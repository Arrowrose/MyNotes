package com.example.mynotes;

import static com.example.mynotes.DataBase.UserDao.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.DataBase.RoomDB;
import com.example.mynotes.DataBase.UserDao;
import com.example.mynotes.Models.User;


public class RegistrationActivity extends AppCompatActivity {

    EditText login, mail, password;
    Button reg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        login = findViewById(R.id.login);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);

        reg_btn = findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setLogin(login.getText().toString());
                user.setMail(mail.getText().toString());
                user.setPassword(password.getText().toString());
                if (validateInput(user)) {
                    RoomDB database = RoomDB.getInstance(getApplicationContext());
                    UserDao userDao = database.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(
                                            RegistrationActivity.this, LogInActivity.class));
                                    Toast.makeText(getApplicationContext(), "Вы зарегистрированы!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Boolean validateInput(User user) {
        if (user.getLogin().isEmpty() ||
        user.getMail().isEmpty() ||
        user.getPassword().isEmpty()) {
            return  false;
        }
        return true;
    }
}