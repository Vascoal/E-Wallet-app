package com.example.projectvasco;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PrefsManager prefs;
    private WalletDbHelper db;
    private TextView tvGreeting, tvBalance, tvEye;
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new PrefsManager(this);
        db = new WalletDbHelper(this);

        // Terapkan mode gelap tersimpan SEBELUM layout dibuat
        AppCompatDelegate.setDefaultNightMode(
                prefs.isDarkMode() ? AppCompatDelegate.MODE_NIGHT_YES
                        : AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        tvGreeting = findViewById(R.id.tvGreeting);
        tvBalance = findViewById(R.id.tvBalance);
        tvEye = findViewById(R.id.tvEye);

        ListView listView = findViewById(R.id.listTransactions);
        adapter = new TransactionAdapter(this, new ArrayList<>());
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.tvEmpty));

        tvEye.setOnClickListener(v -> {
            prefs.setHideBalance(!prefs.isHideBalance());
            refreshBalance();
        });
        findViewById(R.id.btnTopUp).setOnClickListener(v -> openTransaction("TOPUP"));
        findViewById(R.id.btnSend).setOnClickListener(v -> openTransaction("KIRIM"));
        findViewById(R.id.btnSettings).setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }

    // Dibaca ulang setiap kembali dari halaman Pengaturan / Transaksi
    @Override
    protected void onResume() {
        super.onResume();
        tvGreeting.setText("Halo, " + prefs.getUserName() + "!");
        refreshBalance();
        adapter.clear();
        adapter.addAll(db.getAll());
    }

    private void refreshBalance() {
        if (prefs.isHideBalance()) {
            tvBalance.setText("Rp ••••••");
            tvEye.setText("Tampilkan");
        } else {
            tvBalance.setText(Utils.rupiah(db.getBalance()));
            tvEye.setText("Sembunyikan");
        }
    }

    private void openTransaction(String type) {
        Intent i = new Intent(this, TransactionActivity.class);
        i.putExtra("type", type);
        startActivity(i);
    }
}