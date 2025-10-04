package com.loretacafe.pos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ResetPasswordActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextInputEditText etUsername;
    private androidx.appcompat.widget.AppCompatButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Initialize views
        initializeViews();

        // Setup click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        etUsername = findViewById(R.id.etUsername);
        btnContinue = findViewById(R.id.btnContinue);
    }

    private void setupClickListeners() {
        // Back button click
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to previous screen
                finish();
            }
        });

        // Continue button click
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleResetPassword();
            }
        });
    }

    private void handleResetPassword() {
        String username = etUsername.getText().toString().trim();

        // Validate input
        if (username.isEmpty()) {
            etUsername.setError("Email is required");
            etUsername.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            etUsername.setError("Please enter a valid email");
            etUsername.requestFocus();
            return;
        }

        // Perform reset password
        performResetPassword(username);
    }

    private void performResetPassword(String email) {
        // TODO: Implement actual OTP sending logic here
        // This is where you would:
        // 1. Make API call to your backend
        // 2. Send OTP to the email
        // 3. Navigate to OTP verification screen

        // For now, show a success message
        Toast.makeText(this, "OTP sent to " + email, Toast.LENGTH_LONG).show();

        // Navigate to OTP verification screen
        Intent intent = new Intent(ResetPasswordActivity.this, OtpVerificationActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}