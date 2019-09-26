package com.example.newsapp.Fragments

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat

import com.example.newsapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(bundle: Bundle, s: String) {

        addPreferencesFromResource(R.xml.pref_high_contrast)

    }
}
