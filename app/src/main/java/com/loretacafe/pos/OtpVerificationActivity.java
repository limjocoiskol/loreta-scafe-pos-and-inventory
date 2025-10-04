package com.loretacafe.pos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtpVerificationActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etOtp1, etOtp2, etOtp3, etOtp4, etOtp5, etOtp6;
    private TextView tvResendCode;
    private androidx.appcompat.widget.AppCompatButton btnContinue;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Get email from intent
        userEmail = getIntent().getStringExtra("email");

        // Initialize views
        initializeViews();

        // Setup OTP input behavior
        setupOtpInputs();

        // Setup click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        etOtp1 = findViewById(R.id.etOtp1);
        etOtp2 = findViewById(R.id.etOtp2);
        etOtp3 = findViewById(R.id.etOtp3);
        etOtp4 = findViewById(R.id.etOtp4);
        etOtp5 = findViewById(R.id.etOtp5);
        etOtp6 = findViewById(R.id.etOtp6);
        tvResendCode = findViewById(R.id.tvResendCode);
        btnContinue = findViewById(R.id.btnContinue);

        // Focus on first box
        etOtp1.requestFocus();
    }

    private void setupOtpInputs() {
        // Auto-move to next box when typing
        setupOtpBox(etOtp1, etOtp2, null);
        setupOtpBox(etOtp2, etOtp3, etOtp1);
        setupOtpBox(etOtp3, etOtp4, etOtp2);
        setupOtpBox(etOtp4, etOtp5, etOtp3);
        setupOtpBox(etOtp5, etOtp6, etOtp4);
        setupOtpBox(etOtp6, null, etOtp5);
    }

    private void setupOtpBox(final EditText currentBox, final EditText nextBox, final EditText previousBox) {
        currentBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1 && nextBox != null) {
                    nextBox.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        currentBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (currentBox.getText().toString().isEmpty() && previousBox != null) {
                        previousBox.requestFocus();
                        previousBox.setText("");
                    }
                }
                return false;
            }
        });
    }

    private void setupClickListeners() {
        // Back button click
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Resend code click
        tvResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleResendCode();
            }
        });

        // Continue button click
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleVerifyOtp();
            }
        });
    }

    private void handleVerifyOtp() {
        String otp = etOtp1.getText().toString() +
                etOtp2.getText().toString() +
                etOtp3.getText().toString() +
                etOtp4.getText().toString() +
                etOtp5.getText().toString() +
                etOtp6.getText().toString();

        if (otp.length() != 6) {
            Toast.makeText(this, "Please enter complete 6-digit code", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verify OTP
        verifyOtp(otp);
    }

    private void verifyOtp(String otp) {
        // TODO: Implement actual OTP verification logic here
        // This is where you would:
        // 1. Make API call to verify OTP
        // 2. Check if OTP is valid
        // 3. Navigate to new password screen

        // For now, show success message and navigate to new password screen
        Toast.makeText(this, "OTP verified successfully!", Toast.LENGTH_LONG).show();

        // Navigate to New Password screen
        Intent intent = new Intent(OtpVerificationActivity.this, NewPasswordActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
        finish();
    }

    private void handleResendCode() {
        // TODO: Implement resend OTP logic
        // Clear all boxes
        etOtp1.setText("");
        etOtp2.setText("");
        etOtp3.setText("");
        etOtp4.setText("");
        etOtp5.setText("");
        etOtp6.setText("");
        etOtp1.requestFocus();

        Toast.makeText(this, "OTP resent to " + userEmail, Toast.LENGTH_SHORT).show();
    }
}