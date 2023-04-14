package com.example.practice6.data.repository;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesRepository {
    private SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    public SharedPreferencesRepository(Context context) {
        this.sharedPreferences = context.getSharedPreferences("MY_PREFERENCE_NAME", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }


    public void setString(String key, String content){
        editor.putString(key, content);
        editor.apply();
    }

    public String getString(String key){
        return sharedPreferences.getString(key, null);
    }

}
