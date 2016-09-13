package com.pavan.comparezap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class DisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    protected static HashMap<Long, Product> zappos;
    protected static HashMap<Long, Product> pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        recyclerView = (RecyclerView)findViewById(R.id.drawerList);

    }
}
