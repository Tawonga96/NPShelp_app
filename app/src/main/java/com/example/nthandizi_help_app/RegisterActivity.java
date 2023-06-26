package com.example.nthandizi_help_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nthandizi_help_app.models.Registration;

public class RegisterActivity extends AppCompatActivity {
    private EditText fnameEditText, lnameEditText, pnumberEditText, emailEditText, passEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fnameEditText = findViewById(R.id.fname_edittext);
        lnameEditText = findViewById(R.id.lname_edittext);
        pnumberEditText = findViewById(R.id.pnumber_edittext);
        emailEditText = findViewById(R.id.email_edittext);
        passEditText = findViewById(R.id.pass_edittext);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = fnameEditText.getText().toString();
                String lname = lnameEditText.getText().toString();
                String pnumber = pnumberEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passEditText.getText().toString();

                Registration.register(fname, lname, pnumber, email, password);

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
