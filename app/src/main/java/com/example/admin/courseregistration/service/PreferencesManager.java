package com.example.admin.courseregistration.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.courseregistration.model.SubjectModel;

import java.util.ArrayList;

/**
 * Created by trycatch on 2017. 3. 11..
 */

public class PreferencesManager {
    public static PreferencesManager instance;
    private Context mContext;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public PreferencesManager(Context mContext) {
        this.mContext = mContext;
        preferences = mContext.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setID(String id){
        editor.putString("id", id);
        editor.commit();
    }

    public String getID(){
        return preferences.getString("id", "");
    }

    public static PreferencesManager getInstance(Context context) {
        if(instance == null)
            instance = new PreferencesManager(context);
        return instance;
    }
}
