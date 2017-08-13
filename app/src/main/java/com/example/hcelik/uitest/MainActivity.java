package com.example.hcelik.uitest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "UITest";
    private ListView listView;
    private ArrayList<Order> orders;
    private OrderAdapter adapter;
    private ProgressDialog progressDialog = null;
    private Runnable viewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orders = new ArrayList<>();
        this.adapter = new OrderAdapter(this, R.layout.row, orders);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        viewOrders = new Runnable() {
            @Override
            public void run() {
                getOrders();
            }
        };
        Thread thread = new Thread(null, viewOrders, "Background");
        thread.start();
        progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Getting data...", true);
    }

    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            if (orders != null && orders.size() > 0) {
                adapter.notifyDataSetChanged();
                for (int i = 0; i < orders.size(); i++) {
                    adapter.add(orders.get(i));
                }
            }
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    };

    private void getOrders() {
        try {
            orders = new ArrayList<>();
            Order order1 = new Order();
            order1.setOrderName("Services");
            order1.setOrderStatus("Pending");
            Order order2 = new Order();
            order2.setOrderName("Advert");
            order2.setOrderStatus("Completed");
            orders.add(order1);
            orders.add(order2);
            Thread.sleep(2000);
        } catch (Exception e) {
            Log.e(TAG, "getOrders: " + e.getMessage());
        }
        runOnUiThread(returnRes);
    }

}
