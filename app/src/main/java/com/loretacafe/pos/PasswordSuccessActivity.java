package com.loretacafe.pos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordSuccessActivity extends AppCompatActivity {

    private androidx.appcompat.widget.AppCompatButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_success);

        // Initialize views
        initializeViews();

        // Setup click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        btnContinue = findViewById(R.id.btnContinue);
    }

    private void setupClickListeners() {
        // Continue button click
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    private void navigateToLogin() {
        // Navigate back to login screen
        // Clear the activity stack so user can't go back
        Intent intent = new Intent(PasswordSuccessActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // Disable back button on success screen
        // User must click Continue button
        // Do nothing
    }
}