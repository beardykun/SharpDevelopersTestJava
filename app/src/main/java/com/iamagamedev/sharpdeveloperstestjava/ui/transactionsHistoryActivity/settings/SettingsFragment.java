package com.iamagamedev.sharpdeveloperstestjava.ui.transactionsHistoryActivity.settings;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.iamagamedev.sharpdeveloperstestjava.R;

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
