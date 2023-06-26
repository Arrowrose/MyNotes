package com.example.stelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stelp.DataBase.RoomDB;
import com.example.stelp.DataBase.UserDao;
import com.example.stelp.Models.User;


public class RegistrationActivity extends AppCompatActivity {

    EditText login, mail, password, password2;
    Button reg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        login = findViewById(R.id.login);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);

        reg_btn = findViewById(R.id.reg_btn);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setLogin(login.getText().toString());
                user.setMail(mail.getText().toString());
                if (password.length() <= 10 & password.getText().toString().equals(password2.getText().toString())) {
                    user.setPassword(password.getText().toString());
                }  else {
                    Toast.makeText(getApplicationContext(), "Слишком длинный пароль!", Toast.LENGTH_SHORT).show();
                }
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