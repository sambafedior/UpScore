package com.sambafedior.upscore.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.sambafedior.upscore.R;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextLastName;
    private EditText editTextFirstName;
    private EditText editTextemail;
    private EditText editTextphoneNumber;
    private EditText editTextpassword;
    private EditText editTextpasswordConfirm;
    private Button btnTerminer;
    private ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextemail = findViewById(R.id.editTextemail);
        editTextphoneNumber = findViewById(R.id.editTextphone_number);
        editTextpassword = findViewById(R.id.editTextpassword);
        editTextpasswordConfirm = findViewById(R.id.editTextpassword_confirm);
        progressBar = findViewById(R.id.progressBar);

        btnTerminer = findViewById(R.id.btnTerminer);
        btnTerminer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTerminer) {
            String email = editTextemail.getText().toString().trim();
            String password = editTextpassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            //create user
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            DynamicToast.makeSuccess(SignupActivity.this, "Félécitation").show();

                            progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                DynamicToast.makeError(SignupActivity.this, "Authentication échoué." + task.getException()).show();

                            } else {
                                String userId = auth.getCurrentUser().getUid();
                                DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                                String firstName = editTextemail.getText().toString().trim();
                                String lastName = editTextpassword.getText().toString().trim();
                                String phoneNumber = editTextemail.getText().toString().trim();

                                Map newUser = new HashMap();
                                newUser.put("firstName", firstName);
                                newUser.put("lastName", lastName);
                                newUser.put("phoneNumber", phoneNumber);
                                userDb.setValue(newUser);
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
