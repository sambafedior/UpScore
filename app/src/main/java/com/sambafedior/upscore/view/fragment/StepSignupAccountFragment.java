package com.sambafedior.upscore.view.fragment;


import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sambafedior.upscore.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.OnTouch;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepSignupAccountFragment extends Fragment implements Step {
    private static final String TAG = "StepSignupAccountFrag";
    View view;
    @BindView(R.id.editTextBirthDate)
    EditText editTextBirthDate;
    @BindView(R.id.editTextCity)
    EditText editTextCity;
    @BindView(R.id.tvAgence)
    TextView tvAgence;
    @BindView(R.id.tvSociete)
    TextView tvSociete;

    private String current = "";
    private String ddmmyyyy = "JJMMAAAA";
    private Calendar cal = Calendar.getInstance();
    Location location;
    double lat;
    double lng;

    public StepSignupAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_step_signup_account, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @OnTextChanged(R.id.editTextBirthDate)
    void onTextChanged(CharSequence charSequence) {
        if (!charSequence.toString().equals(current)) {
            String clean = charSequence.toString().replaceAll("[^\\d.]|\\.", "");
            String cleanC = current.replaceAll("[^\\d.]|\\.", "");

            int cl = clean.length();
            int sel = cl;
            for (int i = 2; i <= cl && i < 6; i += 2) {
                sel++;
            }
            //Fix for pressing delete next to a forward slash
            if (clean.equals(cleanC)) sel--;

            if (clean.length() < 8) {
                clean = clean + ddmmyyyy.substring(clean.length());
            } else {
                //This part makes sure that when we finish entering numbers
                //the date is correct, fixing it otherwise
                int day = Integer.parseInt(clean.substring(0, 2));
                int mon = Integer.parseInt(clean.substring(2, 4));
                int year = Integer.parseInt(clean.substring(4, 8));

                mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                cal.set(Calendar.MONTH, mon - 1);
                year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                cal.set(Calendar.YEAR, year);
                // ^ first set year for the line below to work correctly
                //with leap years - otherwise, date e.g. 29/02/2012
                //would be automatically corrected to 28/02/2012

                day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                clean = String.format("%02d%02d%02d", day, mon, year);
            }

            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                    clean.substring(2, 4),
                    clean.substring(4, 8));

            sel = sel < 0 ? 0 : sel;
            current = clean;
            editTextBirthDate.setText(current);
            editTextBirthDate.setSelection(sel < current.length() ? sel : current.length());
        }
    }

    @OnTouch(R.id.editTextCity)
    boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (editTextCity.getRight() - editTextCity.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                // your action here
                showCityName();
                return true;
            }
        }
        return false;
    }

    private void showCityName() {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            if (location != null) {
                try {
                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    String cityName = addresses.get(0).getLocality();
                    String stateName = addresses.get(0).getAdminArea();
                    String countryName = addresses.get(0).getCountryName();

                    editTextCity.setText(cityName);
                    Toast.makeText(this.getActivity(), cityName, Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Log.e("Error : Geocoder", "Impossible to connect to Geocoder", e);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // {Some Code}
                }
            }
        }
    }

}



