package com.example.projectvasco;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        WalletDbHelper db = new WalletDbHelper(this);
        String type = getIntent().getStringExtra("type");
        boolean isSend = "KIRIM".equals(type);

        TextView tvFormTitle = findViewById(R.id.tvFormTitle);
        EditText etAmount = findViewById(R.id.etAmount);
        EditText etTarget = findViewById(R.id.etTarget);

        tvFormTitle.setText(isSend ? "Kirim Uang" : "Top Up Saldo");
        etTarget.setVisibility(isSend ? View.VISIBLE : View.GONE);

        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            long amount;
            try {
                amount = Long.parseLong(etAmount.getText().toString());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            if (amount <= 0) {
                etAmount.setError("Masukkan nominal yang valid");
                return;
            }

            if (isSend) {
                String target = etTarget.getText().toString().trim();
                if (target.isEmpty()) {
                    etTarget.setError("Isi nama / nomor penerima");
                    return;
                }
                if (amount > db.getBalance()) {
                    etAmount.setError("Saldo tidak cukup");
                    return;
                }
                db.insert("KIRIM", "Kirim ke " + target, amount);
            } else {
                db.insert("TOPUP", "Top Up Saldo", amount);
            }
            Toast.makeText(this, "Transaksi berhasil", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}