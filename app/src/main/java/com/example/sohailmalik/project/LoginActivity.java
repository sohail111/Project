package com.example.sohailmalik.project;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity {

    private String email = "sohailmalik@gmail.com";
    private String password = "1234";
    private TextInputLayout emailLayout, passwordLayout;
    private EditText emailEditText, passwordEditText;
    private RelativeLayout loginRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAllEditText()) {
                    if (emailEditText.getText().toString().equals(email) && passwordEditText.getText().toString().equals(password)) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Snackbar.make(loginRootLayout, "Email or password do not match", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initViews() {
        emailLayout = (TextInputLayout) findViewById(R.id.emailLayout);
        passwordLayout = (TextInputLayout) findViewById(R.id.passwordLayout);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginRootLayout = (RelativeLayout) findViewById(R.id.loginRootLayout);
    }

    private boolean checkAllEditText() {
        if (emailEditText.getText().toString().isEmpty()) {
            emailLayout.setError("Please type email");
            return false;
        }
        if (passwordEditText.getText().toString().isEmpty()) {
            passwordLayout.setError("Please type password");
            return false;
        }
        return true;
    }
}
