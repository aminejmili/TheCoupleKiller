package com.project.thecouplekiller;

import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class AlgoUtils {

    public static void savePlayerIntoPreference(SharedPreferences aInpreferences, Player aInplayer)
    {

        SharedPreferences.Editor prefsEditor = aInpreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(aInplayer);
        prefsEditor.putString("player", json);
        prefsEditor.commit();
    }

    public static Player getPlayerFromPreference(SharedPreferences aInpreferences)
    {
        Gson gson = new Gson();
        String json = aInpreferences.getString("Player", "");
        Player player = gson.fromJson(json, Player.class);
        return player;
    }


}
