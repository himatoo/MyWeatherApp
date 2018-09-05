package com.example.like2.myweatherapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_main);
        SharedPreferences preferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen screen = getPreferenceScreen();
        int count = screen.getPreferenceCount();
        for (int i = 0; i < count; i++) {
            Preference p = screen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {
                String val = preferences.getString(p.getKey(), "");
                prefsummry(p, val);
            }
        }

    }

    private void prefsummry(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefindex = listPreference.findIndexOfValue(value);
            if (prefindex >= 0) {
                listPreference.setSummary(listPreference.getEntries()[prefindex]);
            }
        } else if (preference instanceof EditTextPreference) {
            preference.setSummary(value);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if (p != null) {
            if (!(p instanceof CheckBoxPreference)) {
                String val = sharedPreferences.getString(p.getKey(), "");
                prefsummry(p, val);
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
