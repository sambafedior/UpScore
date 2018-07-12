package com.sambafedior.upscore.view.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.sambafedior.upscore.R;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private Button btnTerminer;
    ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        auth = FirebaseAuth.getInstance();

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
        editTextEmail = findViewById(R.id.editTextEmail);
        btnTerminer = findViewById(R.id.btnTerminer);
        progressBar = findViewById(R.id.progressBar);
        btnTerminer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTerminer) {
            String email = editTextEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                DynamicToast.makeError(ResetPasswordActivity.this, "Veuillez saisir votre email").show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                DynamicToast.makeSuccess(ResetPasswordActivity.this, "Nous vous avons envoyé des instructions pour réinitialiser votre mot de passe!").show();
                            } else {
                                DynamicToast.makeError(ResetPasswordActivity.this, "Impossible d'envoyer l'e-mail de réinitialisation!").show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        }
    }
}

