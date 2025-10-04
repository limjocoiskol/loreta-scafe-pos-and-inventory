package com.loretacafe.pos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class NewPasswordActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextInputEditText etNewPassword, etConfirmPassword;
    private androidx.appcompat.widget.AppCompatButton btnConfirm;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        // Get email from intent
        userEmail = getIntent().getStringExtra("email");

        // Initialize views
        initializeViews();

        // Setup click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void setupClickListeners() {
        // Back button click
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Confirm button click
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleResetPassword();
            }
        });
    }

    private void handleResetPassword() {
        String newPassword = etNewPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Validation
        if (newPassword.isEmpty()) {
            Toast.makeText(this, "Please enter new password", Toast.LENGTH_SHORT).show();
            etNewPassword.requestFocus();
            return;
        }

        if (newPassword.length() < 8) {
            Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            etNewPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            etConfirmPassword.requestFocus();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            etConfirmPassword.requestFocus();
            return;
        }

        // Reset password
        performPasswordReset(newPassword);
    }

    private void performPasswordReset(String newPassword) {
        // TODO: Implement actual password reset logic here
        // This is where you would:
        // 1. Make API call to reset password
        // 2. Send email and new password to backend
        // 3. Handle success/error response

        // For now, navigate to success screen
        Intent intent = new Intent(NewPasswordActivity.this, PasswordSuccessActivity.class);
        startActivity(intent);
        finish();
    }
}