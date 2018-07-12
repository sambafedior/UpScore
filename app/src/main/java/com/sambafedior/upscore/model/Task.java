package com.sambafedior.upscore.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Task {
    private String name;
    private String description;
    private String status;
    private String startDate;
    private String endDate;
}
