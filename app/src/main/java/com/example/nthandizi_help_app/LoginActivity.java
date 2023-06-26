package com.example.nthandizi_help_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nthandizi_help_app.models.Login;

public class LoginActivity extends AppCompatActivity {
    private EditText userEditText, passwordEditText;
    private Button loginButton;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEditText = findViewById(R.id.etUsername);
        passwordEditText = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Login.login(fname, password, LoginActivity.this);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
