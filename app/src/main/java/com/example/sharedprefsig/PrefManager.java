package com.example.sharedprefsig;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREFS_FILENAME = "AutoAppPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private final SharedPreferences sharedPreferences;

    private static volatile PrefManager instance;

    private PrefManager(Context context){
        sharedPreferences =context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE);
    }

    public static PrefManager getInstance(Context context){
        if (instance == null){
            synchronized (PrefManager.class){
                if (instance == null){
                    instance = new PrefManager(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void  SaveUsername(String username){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public void  SavePassword(String pwd){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PASSWORD, pwd);
        editor.apply();
    }

    public String getUsername(){
        return sharedPreferences.getString(KEY_USERNAME,"");
    }

    public String getPassword(){
        return sharedPreferences.getString(KEY_PASSWORD,"");
    }

    public void setKeyIsLoggedIn(boolean isLoggedIn){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN,false);
    }

    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
