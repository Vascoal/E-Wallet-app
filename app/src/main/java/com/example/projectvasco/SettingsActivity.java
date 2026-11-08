package com.example.projectvasco;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        PrefsManager prefs = new PrefsManager(this);
        EditText etName = findViewById(R.id.etName);
        SwitchCompat swDark = findViewById(R.id.swDark);
        SwitchCompat swHide = findViewById(R.id.swHide);

        // Tampilkan nilai yang tersimpan
        etName.setText(prefs.getUserName());
        swDark.setChecked(prefs.isDarkMode());
        swHide.setChecked(prefs.isHideBalance());

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) name = "Pengguna";

            prefs.setUserName(name);              // apply() dipanggil di PrefsManager
            prefs.setDarkMode(swDark.isChecked());
            prefs.setHideBalance(swHide.isChecked());

            AppCompatDelegate.setDefaultNightMode(
                    swDark.isChecked() ? AppCompatDelegate.MODE_NIGHT_YES
                            : AppCompatDelegate.MODE_NIGHT_NO);
            Toast.makeText(this, "Pengaturan disimpan", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}