package com.example.nthandizi_help_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nthandizi_help_app.models.ReportSender;

public class HomeActivity extends AppCompatActivity {
    private EditText messageEditText;
    private RadioGroup colorRadioGroup;
    private Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        messageEditText = findViewById(R.id.messageEditText);
        colorRadioGroup = findViewById(R.id.colorRadioGroup);
        sendButton = findViewById(R.id.sendReportButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                int selectedColor = getColorFromRadioButton();
                // Perform your desired action here
                // For example, sending a report with the message and selected color
                ReportSender.sendReport(message, selectedColor);
                Toast.makeText(HomeActivity.this, "Report sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private int getColorFromRadioButton() {
        int selectedRadioButtonId = colorRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        switch (selectedRadioButton.getId()) {
            case R.id.redRadioButton:
                return Color.RED;
            case R.id.yellowRadioButton:
                return Color.YELLOW;
            case R.id.greenRadioButton:
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
    }
}