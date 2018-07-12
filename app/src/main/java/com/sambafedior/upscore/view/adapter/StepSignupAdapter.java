package com.sambafedior.upscore.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.sambafedior.upscore.view.fragment.StepSignupAccountFragment;
import com.sambafedior.upscore.view.fragment.StepSignupHobbieFragment;
import com.sambafedior.upscore.view.fragment.StepSignupPaymentFragment;
import com.sambafedior.upscore.view.fragment.StepSignupSocialFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class StepSignupAdapter extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "position";

    public StepSignupAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {


        switch (position) {
            case 0:
                final StepSignupAccountFragment stepSignupAccountFragment = new StepSignupAccountFragment();
                Bundle bundleAccount = new Bundle();
                bundleAccount.putInt(CURRENT_STEP_POSITION_KEY, position);
                stepSignupAccountFragment.setArguments(bundleAccount);
                return stepSignupAccountFragment;

            case 1:
                final StepSignupHobbieFragment stepSignupHobbieFragment = new StepSignupHobbieFragment();
                Bundle bundleHobbie = new Bundle();
                bundleHobbie.putInt(CURRENT_STEP_POSITION_KEY, position);
                stepSignupHobbieFragment.setArguments(bundleHobbie);
                return stepSignupHobbieFragment;

            case 2:
                final StepSignupSocialFragment stepSignupSocialFragment = new StepSignupSocialFragment();
                Bundle bundleSocial = new Bundle();
                bundleSocial.putInt(CURRENT_STEP_POSITION_KEY, position);
                stepSignupSocialFragment.setArguments(bundleSocial);
                return stepSignupSocialFragment;
            case 3:
                final StepSignupPaymentFragment stepSignupPaymentFragment = new StepSignupPaymentFragment();
                Bundle bundlePayment = new Bundle();
                bundlePayment.putInt(CURRENT_STEP_POSITION_KEY, position);
                stepSignupPaymentFragment.setArguments(bundlePayment);
                return stepSignupPaymentFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {

        switch (position) {
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("Account") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Hobbie")
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Social")
                        .create();
            case 3:
                return new StepViewModel.Builder(context)
                        .setTitle("Payment")
                        .create();
        }

        return null;
    }
}
