package com.sambafedior.upscore.view.fragment;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sambafedior.upscore.R;
import com.sambafedior.upscore.model.Hobbie;
import com.sambafedior.upscore.model.Tag;
import com.sambafedior.upscore.view.adapter.HobbieAdapter;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepSignupHobbieFragment extends Fragment implements Step {
    private static final String TAG = "StepSignupHobbie";
    View view;
    RecyclerView recyclerView;
    HobbieAdapter adapter;
    List<Hobbie> hobbieList = new ArrayList<>();

    public StepSignupHobbieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_step_signup_hobbie, container, false);
        initRecyclerViewHobbie();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListHobbie(getDataHobbie());
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

    private void initRecyclerViewHobbie() {
        recyclerView = view.findViewById(R.id.recyclerViewHobbie);
        adapter = new HobbieAdapter(getActivity(), hobbieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setListHobbie(List<Hobbie> ListData) {
        if (!this.hobbieList.isEmpty()) {
            this.hobbieList.clear();
            adapter.notifyDataSetChanged();
        }
        for (Hobbie list : ListData) {
            this.hobbieList.add(list);
            adapter.notifyItemInserted(this.hobbieList.lastIndexOf(list));
        }
    }

    public List<Hobbie> getDataHobbie() {
        List<Hobbie> hobbies = new ArrayList<>();
        try {
            // Load data
            JSONArray listObjHobbies = new JSONArray(loadJsonFromAsset());
            for (int i = 0; i < listObjHobbies.length(); i++) {
                JSONObject objetHobbie = listObjHobbies.getJSONObject(i);
                JSONArray arrayHobbie = objetHobbie.getJSONArray("tag");

                for (int j = 0; j < objetHobbie.length(); j++) {
                    String objetTag = arrayHobbie.getString(j);
                    Hobbie hobbieTag = new Hobbie(Hobbie.TAG_TYPE, objetTag);
                    hobbies.add(hobbieTag);
                }
                Hobbie hobbieType = new Hobbie(objetHobbie.getString("hobbie"), Hobbie.SECTION_TYPE);
                hobbies.add(hobbieType);
            }
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return hobbies;
    }

    public String loadJsonFromAsset() {
        String json = null;
        try {
            AssetManager assetManager = getActivity().getAssets();
            InputStream is = assetManager.open("hobbie.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
