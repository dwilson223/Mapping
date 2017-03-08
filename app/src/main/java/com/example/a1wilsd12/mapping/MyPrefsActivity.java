package com.example.a1wilsd12.mapping;

/**
 * Created by 1wilsd12 on 08/03/2017.
 */

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class MyPrefsActivity extends PreferenceActivity
{
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Likely to be outdated soon

        addPreferencesFromResource(R.xml.preferences);
    }
}