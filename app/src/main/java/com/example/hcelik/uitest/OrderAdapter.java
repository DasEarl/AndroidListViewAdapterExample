package com.example.hcelik.uitest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hcelik on 13.08.17
 */

public class OrderAdapter extends ArrayAdapter<Order> {
    private static final String TAG = "UITest";
    private final ArrayList<Order> items;
    private final Context context;

    public OrderAdapter(Context context, int textViewResourceId, ArrayList<Order> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.context = context;
        Log.d(TAG, "OrderAdapter: Init");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row, null);
        }
        Order order = items.get(position);
        TextView topText = view.findViewById(R.id.topText);
        TextView bottomText = view.findViewById(R.id.bottomText);
        topText.setText("Name: " + order.getOrderName());
        bottomText.setText("Status: " + order.getOrderStatus());
        return view;
    }
}
