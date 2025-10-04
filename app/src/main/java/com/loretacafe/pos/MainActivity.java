package com.loretacafe.pos;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etUsername;
    private TextInputEditText etPassword;
    private Button btnContinue;
    private TextView tvForgotPassword;
    private CheckBox cbTerms;
    private TextView tvTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();

        // Setup terms and privacy text
        setupTermsAndPrivacy();

        // Setup click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnContinue = findViewById(R.id.btnContinue);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        cbTerms = findViewById(R.id.cbTerms);
        tvTerms = findViewById(R.id.tvTerms);
    }

    private void setupTermsAndPrivacy() {
        String text = "By clicking continue, you agree to our Terms of Service and Privacy Policy";
        SpannableString spannableString = new SpannableString(text);

        // Terms of Service clickable span
        ClickableSpan termsClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Handle Terms of Service click
                Toast.makeText(MainActivity.this, "Terms of Service clicked", Toast.LENGTH_SHORT).show();
                // You can open a new activity or web view here
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(0xFF5D4E3C);
                ds.setUnderlineText(false);
            }
        };

        // Privacy Policy clickable span
        ClickableSpan privacyClickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Handle Privacy Policy click
                Toast.makeText(MainActivity.this, "Privacy Policy clicked", Toast.LENGTH_SHORT).show();
                // You can open a new activity or web view here
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(0xFF5D4E3C);
                ds.setUnderlineText(false);
            }
        };

        // Apply spans
        int termsStart = text.indexOf("Terms of Service");
        int termsEnd = termsStart + "Terms of Service".length();
        spannableString.setSpan(termsClickableSpan, termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int privacyStart = text.indexOf("Privacy Policy");
        int privacyEnd = privacyStart + "Privacy Policy".length();
        spannableString.setSpan(privacyClickableSpan, privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvTerms.setText(spannableString);
        tvTerms.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setupClickListeners() {
        // Continue button click
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        // Forgot password click - NAVIGATE TO RESET PASSWORD SCREEN
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleForgotPassword();
            }
        });
    }

    private void handleLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate inputs
        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        if (!cbTerms.isChecked()) {
            Toast.makeText(this, "Please agree to Terms of Service and Privacy Policy", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform login
        performLogin(username, password);
    }

    private void performLogin(String username, String password) {
        // TODO: Implement actual login logic here
        // This is where you would:
        // 1. Make API call to your backend
        // 2. Validate credentials
        // 3. Store session/token
        // 4. Navigate to main activity

        // For now, show a toast
        Toast.makeText(this, "Login successful! Welcome to Loreta's Café", Toast.LENGTH_LONG).show();

        // Example: Navigate to dashboard/home activity on successful login
        // Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        // startActivity(intent);
        // finish();
    }

    private void handleForgotPassword() {
        // Navigate to Reset Password screen
        Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }
}