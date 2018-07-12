package com.sambafedior.upscore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.sambafedior.upscore.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageViewLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnLogin;
    private TextView textViewForgotPassword;
    private TextView textViewCreateAccount;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        setActionBar();
        initViews();


    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setTitle("");
        }
    }

    private void initViews() {
        imageViewLogin = findViewById(R.id.imageViewLogin);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        progressBar = findViewById(R.id.progressBar);
        btnLogin.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
        textViewCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {

            String email = editTextEmail.getText().toString();
            final String password = editTextPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                DynamicToast.makeError(LoginActivity.this, "Veuillez saisir votre adresse email").show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                DynamicToast.makeError(LoginActivity.this, "Veuillez saisir votre adresse mots de passe").show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                // there was an error
                                if (password.length() < 6) {
                                    //  editTextPassword.setError("");
                                    DynamicToast.makeError(LoginActivity.this, "Le mot de passe doit contenir au moins 6 caractères!").show();
                                } else {
                                    DynamicToast.makeError(LoginActivity.this, getString(R.string.auth_failed)).show();
                                }
                            } else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
        }


        if (view.getId() == R.id.textViewCreateAccount) {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        }

        if (view.getId() == R.id.textViewForgotPassword) {
            startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
