package com.example.projectvasco;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private final SimpleDateFormat fmt =
            new SimpleDateFormat("dd MMM yyyy, HH:mm", new Locale("in", "ID"));

    public TransactionAdapter(Context context, List<Transaction> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_transaction, parent, false);
        }
        Transaction t = getItem(position);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvAmount = convertView.findViewById(R.id.tvAmount);

        tvTitle.setText(t.title);
        tvDate.setText(fmt.format(new Date(t.createdAt)));
        if (t.type.equals("TOPUP")) {
            tvAmount.setText("+ " + Utils.rupiah(t.amount));
            tvAmount.setTextColor(Color.parseColor("#2E7D32"));
        } else {
            tvAmount.setText("- " + Utils.rupiah(t.amount));
            tvAmount.setTextColor(Color.parseColor("#C62828"));
        }
        return convertView;
    }
}