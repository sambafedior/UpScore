package com.sambafedior.upscore.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sambafedior.upscore.R;
import com.sambafedior.upscore.view.activity.SignupInformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @BindView(R.id.imageViewInfo)
    ImageView imageViewInfo;
    @BindView(R.id.imageViewAvatar)
    CircleImageView imageViewAvatar;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.imageViewSetting)
    ImageView imageViewSetting;
    @BindView(R.id.cardViewAdd)
    CardView cardViewAdd;
    @BindView(R.id.cardExplorer)
    CardView cardSearch;
    @BindView(R.id.cardViewMoney)
    CardView cardViewMoney;
    @BindView(R.id.cardViewCalendar)
    CardView cardViewCalendar;
    @BindView(R.id.cardViewMessagerie)
    CardView cardViewMessagerie;
    @BindView(R.id.cardViewMission)
    CardView cardViewMission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }


    @OnClick(R.id.imageViewAvatar)
    void goToAvatar() {

    }

    @OnClick(R.id.imageViewInfo)
    void goToInfo() {
        startActivity(new Intent(MainActivity.this, SignupInformationActivity.class));
    }

    @OnClick(R.id.imageViewSetting)
    void goToSetting() {

    }

    @OnClick(R.id.cardExplorer)
    void goToSearch() {

    }

    @OnClick(R.id.cardViewMoney)
    void goToMoney() {

    }

    @OnClick(R.id.cardViewCalendar)
    void goToCalendar() {

    }

    @OnClick(R.id.cardViewMessagerie)
    void goToMessagerie() {

    }

    @OnClick(R.id.cardViewMission)
    void goToMission() {

    }

    @OnClick(R.id.cardViewAdd)
    void goToAdd() {

    }

}
