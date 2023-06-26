package com.example.stelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stelp.DataBase.RoomDB;
import com.example.stelp.DataBase.UserDao;
import com.example.stelp.Models.Notes;
import com.example.stelp.Models.User;

public class EditProfileActivity extends AppCompatActivity {

    Context context;
    Button save_e;
    TextView login_e, mail_e, password_e, password2_e;
    ImageView back_btn_e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        login_e = findViewById(R.id.login_e);
        mail_e = findViewById(R.id.mail_e);
        password_e = findViewById(R.id.password_e);
        password2_e = findViewById(R.id.password_e);

        back_btn_e = findViewById(R.id.back_btn_e);
        back_btn_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        RoomDB database = RoomDB.getInstance(context);
        UserDao userDao = database.userDao();
        User user = userDao.getUserById();
        String login = user.getLogin();
        String mail = user.getMail();
        String password = user.getPassword();
        login_e.setText(login);
        mail_e.setText(mail);
        password_e.setText(password);

        save_e = findViewById(R.id.save_e);
        save_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        }

        private Boolean validateInput(User user) {
            if (user.getLogin().isEmpty() ||
                    user.getMail().isEmpty() ||
                    user.getPassword().isEmpty()) {
                return  false;
            }
            return true;
        }
        });
    }
}