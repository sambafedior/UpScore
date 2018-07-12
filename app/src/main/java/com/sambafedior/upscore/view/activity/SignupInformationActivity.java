package com.sambafedior.upscore.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sambafedior.upscore.R;
import com.sambafedior.upscore.view.adapter.StepSignupAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class SignupInformationActivity extends AppCompatActivity implements StepperLayout.StepperListener {
    private StepperLayout stepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_information);

        stepperLayout = findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(new StepSignupAdapter(getSupportFragmentManager(), this));
        stepperLayout.setListener(this);
    }

    @Override
    public void onCompleted(View completeButton) {
        Toast.makeText(this, "onCompleted!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(VerificationError verificationError) {
        Toast.makeText(this, "onError! -> " + verificationError.getErrorMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStepSelected(int newStepPosition) {
        Toast.makeText(this, "onStepSelected! -> " + newStepPosition, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReturn() {
        finish();
    }
}
